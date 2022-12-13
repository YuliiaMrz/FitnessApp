package com.example.a2assignment2.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a2assignment2.R

object FragmentManager {
    var currentFragment: Fragment? = null
    fun setFragment(newFragment: Fragment, act:AppCompatActivity){
        //switching between fragments
        val transaction = act.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeholder, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }
}