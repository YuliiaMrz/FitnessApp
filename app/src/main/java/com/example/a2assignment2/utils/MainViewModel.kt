package com.example.a2assignment2.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2assignment2.adapters.ExerciseModel

class MainViewModel: ViewModel() {
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()
}