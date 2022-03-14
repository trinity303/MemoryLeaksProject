package com.example.memory.leaks.project.sender.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.memory.leaks.project.R
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author kebirova@kolesa.kz
 */
class DetailsSenderActivity: AppCompatActivity(R.layout.activity_details_sender) {

    private val viewModel: DetailsSenderViewModel by viewModel()

    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sendButton = findViewById(R.id.activity_details_sender_button)
        sendButton.setOnClickListener {
            viewModel.onSendClicked()
            finish()
        }
    }
}