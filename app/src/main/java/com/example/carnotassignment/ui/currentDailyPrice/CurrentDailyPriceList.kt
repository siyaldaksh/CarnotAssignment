package com.example.carnotassignment.ui.currentDailyPrice

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import carnotassignment.R
import carnotassignment.databinding.CurrentDailyPriceListFragmentBinding
import com.example.carnotassignment.Utils.CarnotConstants
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrentDailyPriceList : Fragment() {

    private val viewModel: CurrentDailyPriceListViewModel by viewModels()
    private lateinit var binding: CurrentDailyPriceListFragmentBinding
    private lateinit var adapter: Adapter
    private var isState : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.current_daily_price_list_fragment, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            mainViewModel = viewModel
            searchLayout.mainViewModel = viewModel

            stateSearch.setOnClickListener {
                isState = true
                searchLayout.searchBar.visibility = View.VISIBLE
                searchLayout.autoCompleteTextView.hint = "Search state"
            }

            districtSearch.setOnClickListener {
                isState = false
                searchLayout.searchBar.visibility = View.VISIBLE
                searchLayout.autoCompleteTextView.hint = "Search district"
            }

            toolbar.title = "Current Price"
            toolbar.overflowIcon = ContextCompat.getDrawable(requireContext(),R.drawable.overflow_menu_vector)
            toolbar.inflateMenu(R.menu.main)

            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

            toolbar.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.priceDesc -> {
                        viewModel.ascDesc.value = "desc"
                        loadSortedDataByPrice()
                    }

                    R.id.priceAsc -> {
                        viewModel.ascDesc.value = "asc"
                        loadSortedDataByPrice()
                    }

                    R.id.dateDesc -> {
                        viewModel.ascDesc.value = "desc"
                        loadSortedDataByDate()
                    }

                    R.id.dateAsc -> {
                        viewModel.ascDesc.value = "asc"
                        loadSortedDataByDate()
                    }

                }
                true
            }

            searchLayout.autoCompleteTextView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().trim()
                            .isNotEmpty()
                    ) {
                        viewModel.searchText.value = p0.toString()
                    }else{
                        loadAllData()
                    }
                }

            })

            swipeRefreshList.setOnRefreshListener {
                CarnotConstants.offset = 0
                adapter.refresh()

            }

        }



        viewModel.searchClickedLiveData.observe(viewLifecycleOwner,{
            if (it){
                if (isState){
                    loadStateData()
                }else{
                    loadDistrictData()
                }

                viewModel.searchClicked.value = false
            }
        })

        adapter = Adapter()

        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = adapter


        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading) {
                if (!binding.swipeRefreshList.isRefreshing)
                    binding.progressBar.visibility = View.VISIBLE

            }

            else {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshList.isRefreshing = false

                if (loadState.prepend.endOfPaginationReached){
                    if (adapter.itemCount<1){
                        binding.noDataText.visibility = View.VISIBLE
                    }else{
                        binding.noDataText.visibility = View.GONE
                    }
                }
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                errorState?.let {
                    if (!isInternetAvailable()){
                        Toast.makeText(requireContext(), "Please check your internet", Toast.LENGTH_LONG)
                            .show()
                    }else {
                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }
        }

        loadAllData()


        return binding.root
    }

    private fun loadAllData(){
        CarnotConstants.offset = 0
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentDailyPrice.collectLatest { pagedData ->
                adapter.submitData(pagedData)

            }
        }
    }

    private fun loadStateData(){
        CarnotConstants.offset = 0
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentDailyPriceByState.collectLatest { pagedData ->
                adapter.submitData(pagedData)

            }
        }
    }

    private fun loadDistrictData(){
        CarnotConstants.offset = 0
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentDailyPriceByDistrict.collectLatest { pagedData ->
                adapter.submitData(pagedData)

            }
        }
    }

    private fun loadSortedDataByPrice(){
        CarnotConstants.offset = 0
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentDailyPriceSortedByMinPrice.collectLatest { pagedData ->
                adapter.submitData(pagedData)

            }
        }
    }

    private fun loadSortedDataByDate(){
        CarnotConstants.offset = 0
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentDailyPriceSortedByDate.collectLatest { pagedData ->
                adapter.submitData(pagedData)

            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}