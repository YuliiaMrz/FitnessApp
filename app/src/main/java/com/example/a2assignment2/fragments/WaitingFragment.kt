package com.example.a2assignment2.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2assignment2.R
import com.example.a2assignment2.adapters.DayModel
import com.example.a2assignment2.adapters.DaysAdapter
import com.example.a2assignment2.adapters.ExerciseAdapter
import com.example.a2assignment2.databinding.ExerciseListFragmentBinding
import com.example.a2assignment2.databinding.FragmentDaysBinding
import com.example.a2assignment2.databinding.WaitingFragmentBinding
import com.example.a2assignment2.utils.FragmentManager
import com.example.a2assignment2.utils.MainViewModel
import com.example.a2assignment2.utils.TimeUtils

const val COUNT_DOWN_TIME = 11000L
class WaitingFragment : Fragment() {
    private lateinit var binding: WaitingFragmentBinding
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WaitingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pBar.max = COUNT_DOWN_TIME.toInt()
        startTimer()

    }
    //starting timer
    private fun startTimer() = with(binding){
        timer = object : CountDownTimer(COUNT_DOWN_TIME, 1) {
            //updating progressbar
            override fun onTick(restTime: Long) {
                tvTimer.text = TimeUtils.getTime(restTime)
                pBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(ExerciseFragment.newInstance(), activity as AppCompatActivity)
            }

        }.start()
    }
    //cancelling timer when exiting
    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WaitingFragment()

            }

}