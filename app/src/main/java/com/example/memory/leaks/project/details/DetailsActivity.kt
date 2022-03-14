package com.example.memory.leaks.project.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memory.leaks.project.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.memory.leaks.project.common.broadcast.MyReceiver
import com.example.memory.leaks.project.common.wrapper.ResourceWrapper
import com.example.memory.leaks.project.common.wrapper.DefaultResourceWrapper
import com.example.memory.leaks.project.common.user.User
import com.example.memory.leaks.project.details.data.DetailsRepository
import com.example.memory.leaks.project.details.model.Details
import java.lang.Exception

private const val FILTER = "android.net.conn.CONNECTIVITY_CHANGE"
private const val ID_FORMAT = "Id: %s"

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {

    private lateinit var broadcastReceiver: BroadcastReceiver

    private lateinit var dataContainer: ViewGroup
    private lateinit var loadButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var nameTextView: TextView
    private lateinit var surnameTextView: TextView
    private lateinit var idTextView: TextView

    private lateinit var user: User

    private val detailsRepository: DetailsRepository = DetailsRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        broadcastReceiver = MyReceiver()

        val resourceWrapper: ResourceWrapper = DefaultResourceWrapper(this)
        User.createInstance(resourceWrapper)
        user = User.instance

        initViews()
        setName()

        if (savedInstanceState == null) {
            InitNotification().initialize(applicationContext)
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(
                broadcastReceiver,
                IntentFilter(packageName + FILTER)
        )
    }

    private fun initViews() {
        dataContainer = findViewById(R.id.activity_details_data_container)

        loadButton = findViewById(R.id.activity_details_button_load)
        loadButton.setOnClickListener {
            dataContainer.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val details: Details = detailsRepository.loadDetails()
                    launch(Dispatchers.Main) {
                        showData(details)
                        progressBar.visibility = View.GONE
                        dataContainer.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    launch(Dispatchers.Main) {
                        showError(e)
                        progressBar.visibility = View.GONE
                        dataContainer.visibility = View.VISIBLE
                    }
                }
            }
        }

        progressBar = findViewById(R.id.activity_details_progress_bar)
        nameTextView = findViewById(R.id.activity_details_name)
        surnameTextView = findViewById(R.id.activity_details_surname)
        idTextView = findViewById(R.id.activity_details_id)
    }

    private fun setName() {
        nameTextView.text = user.userName
    }

    private fun showData(details: Details) {
        surnameTextView.text = details.surname
        idTextView.text = String.format(ID_FORMAT, details.id)
    }

    private fun showError(exception: Exception) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
    }

    inner class InitNotification {

        fun initialize(context: Context) {
            Thread {
                //imagine that this is long work
                Thread.sleep(3000)

                runOnUiThread {
                    Toast.makeText(context, "Notification was initialize", Toast.LENGTH_LONG).show()
                }
            }.start()
        }
    }
    

}