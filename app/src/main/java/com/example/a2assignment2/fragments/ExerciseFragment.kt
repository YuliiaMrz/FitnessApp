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
import com.example.a2assignment2.adapters.ExerciseModel
import com.example.a2assignment2.databinding.ExerciseBinding
import com.example.a2assignment2.databinding.ExerciseListFragmentBinding
import com.example.a2assignment2.databinding.FragmentDaysBinding
import com.example.a2assignment2.utils.FragmentManager
import com.example.a2assignment2.utils.MainViewModel
import com.example.a2assignment2.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable

class ExerciseFragment : Fragment() {
    private var timer: CountDownTimer? = null
    private lateinit var binding: ExerciseBinding
    private var exerciseCounter = 0
    private var exList: ArrayList<ExerciseModel>? = null
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.mutableListExercise.observe(viewLifecycleOwner) {
            exList = it
            nextExercise()
        }
        binding.bNext.setOnClickListener() {
            nextExercise()
        }
    }

    private fun nextExercise() {
        if(exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }

    private fun setExerciseType(exercise: ExerciseModel) {
        if(exercise.time.startsWith("x")) {
            binding.tvTime.text = exercise.time
        } else {
            startTimer(exercise)
        }
    }

    //starting timer
    private fun startTimer(exercise: ExerciseModel) = with(binding){
        progressBar.max = exercise.time.toInt() * 1000
        timer?.cancel()
        timer = object : CountDownTimer(exercise.time.toLong() * 1000, 1) {
            //updating progressbar
            override fun onTick(restTime: Long) {
                tvTime.text = TimeUtils.getTime(restTime)
                progressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }

        }.start()
    }
    //stopping timer when closed app
    override fun onDetach() {
        super.onDetach()
        timer?.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseFragment()

            }

}