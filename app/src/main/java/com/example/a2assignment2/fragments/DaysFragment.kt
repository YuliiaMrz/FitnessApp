package com.example.a2assignment2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2assignment2.R
import com.example.a2assignment2.adapters.DayModel
import com.example.a2assignment2.adapters.DaysAdapter
import com.example.a2assignment2.adapters.ExerciseModel
import com.example.a2assignment2.databinding.FragmentDaysBinding
import com.example.a2assignment2.utils.FragmentManager
import com.example.a2assignment2.utils.MainViewModel

class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    //element position and adapter
    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_exercises).forEach {
            tArray.add(DayModel(it,0, false))
        }
        return tArray
    }

    private fun fillExerciseList(day: DayModel){
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray((R.array.exercise))
            val exercise = exerciseList[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], false,  exerciseArray[2]))
        }
        model.mutableListExercise.value = tempList

    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()

            }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
        FragmentManager.setFragment(ExerciseListFragment.newInstance(), activity as AppCompatActivity)
    }

}