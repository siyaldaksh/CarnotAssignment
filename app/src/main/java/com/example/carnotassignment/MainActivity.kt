package com.example.carnotassignment

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import carnotassignment.R
import com.example.carnotassignment.ui.currentDailyPrice.CurrentDailyPriceList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrentDailyPriceList())
                .commitNow()
        }
    }

}