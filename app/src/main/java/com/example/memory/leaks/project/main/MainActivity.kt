package com.example.memory.leaks.project.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.memory.leaks.project.R
import com.example.memory.leaks.project.common.user.User
import com.example.memory.leaks.project.details.DetailsActivity
import com.example.memory.leaks.project.main.presentation.MainContract
import com.example.memory.leaks.project.main.presentation.MainPresenter
import com.example.memory.leaks.project.sender.presentation.DetailsSenderActivity
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View {

    private val user: User by lazy { User.instance }

    private lateinit var presenter: MainContract.Presenter

    private lateinit var userNameTextView: TextView
    private lateinit var goToDetailsButton: Button
    private lateinit var calculateResultTextView: TextView
    private lateinit var calculateButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var openSendScreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter(viewListener = this)

        initViews()
    }

    override fun showSuccessLoggerInitMessage() {
        Toast.makeText(this, "Logger was initialized", Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        calculateResultTextView.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        calculateResultTextView.visibility = View.VISIBLE
    }

    override fun showCalculatedResult(result: Int) {
        val str = getString(
                R.string.activity_main_calculated_result_fmt,
                result.toString()
        )

        calculateResultTextView.text = str
    }

    private fun initViews() {
        userNameTextView = findViewById(R.id.activity_main_name)
        userNameTextView.text = user.userName

        goToDetailsButton = findViewById(R.id.activity_main_go_to_details)
        goToDetailsButton.setOnClickListener {
            val intent = Intent(applicationContext, DetailsActivity::class.java)
            startActivity(intent)
        }

        calculateResultTextView = findViewById(R.id.activity_details_calculate_result)

        calculateButton = findViewById(R.id.activity_main_calculate)
        calculateButton.setOnClickListener {
            presenter.onCalculateClicked()
        }

        progressBar = findViewById(R.id.activity_details_calculate_result_progress)

        openSendScreenButton = findViewById(R.id.activity_main_open_sender)
        openSendScreenButton.setOnClickListener {
            startActivity(Intent(this, DetailsSenderActivity::class.java))
        }
    }
}