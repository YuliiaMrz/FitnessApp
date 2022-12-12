package com.example.a2assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a2assignment2.fragments.DaysFragment
import com.example.a2assignment2.utils.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //opening Fragment
        FragmentManager.setFragment(DaysFragment.newInstance(),this)
    }
}