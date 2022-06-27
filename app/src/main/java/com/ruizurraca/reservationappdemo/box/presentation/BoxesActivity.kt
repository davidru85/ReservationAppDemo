package com.ruizurraca.reservationappdemo.box.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruizurraca.reservationappdemo.box.presentation.models.BoxResponseModel
import com.ruizurraca.reservationappdemo.classes.presentation.ClassesActivity
import com.ruizurraca.reservationappdemo.common.Prefs
import com.ruizurraca.reservationappdemo.common.saveBox
import com.ruizurraca.reservationappdemo.databinding.ActivityBoxesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoxesActivity : AppCompatActivity() {
    companion object {
        const val TAG = "BoxesActivity"
    }

    private lateinit var binding: ActivityBoxesBinding
    private val viewModel by viewModels<BoxesViewModel>()
    private val boxesAdapter = BoxesAdapter().apply {
        listener = object : BoxesClickListener {
            override fun boxClick(currentBox: BoxResponseModel) {
                selectBox(currentBox)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoxesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initBindings()
    }

    override fun onStart() {
        super.onStart()
        requestBoxes()
    }

    private fun initBindings() {
        binding.rvBoxesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = boxesAdapter
        }
    }

    private fun initObservers() {
        viewModel.boxes.observe(this, {
            it?.boxesList?.let { list ->
                Log.d(TAG, "initObservers: $list")
                boxesAdapter.fillData(list)
            }
        })
    }

    private fun goToMain() {
        startActivity(Intent(this, ClassesActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    private fun requestBoxes() {
        viewModel.getBoxes()
    }

    private fun selectBox(currentBox: BoxResponseModel) {
        Prefs.saveBox(currentBox)
        goToMain()
    }
}
