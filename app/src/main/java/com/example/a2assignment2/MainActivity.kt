package com.example.a2assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.a2assignment2.fragments.DaysFragment
import com.example.a2assignment2.utils.FragmentManager
import com.example.a2assignment2.utils.MainViewModel

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.pref = getSharedPreferences("main", MODE_PRIVATE)
        //opening Fragment
        FragmentManager.setFragment(DaysFragment.newInstance(),this)
    }
    //checking which fragment is on
    override fun onBackPressed() {
        if(FragmentManager.currentFragment is DaysFragment)super.onBackPressed()
        else FragmentManager.setFragment(DaysFragment.newInstance(), this)

    }
}