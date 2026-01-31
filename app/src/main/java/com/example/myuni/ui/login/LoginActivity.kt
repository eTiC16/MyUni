package com.example.myuni.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myuni.databinding.ActivityLoginBinding
import com.example.myuni.ui.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    private val campusValues = listOf("footscray", "sydney", "ort")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCampusSpinner()
        setupListeners()
        observeViewModel()
    }

    private fun setupCampusSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            campusValues
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        binding.spCampus.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val campus = campusValues[binding.spCampus.selectedItemPosition]
            viewModel.login(campus, username, password)
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this) { loading ->
            binding.progress.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(this) { error ->
            binding.tvError.apply {
                text = error
                visibility = if (error == null) View.GONE else View.VISIBLE
            }
        }
        viewModel.loginSuccess.observe(this) { keypass ->
            keypass?.let {
                val intent = Intent(this, DashboardActivity::class.java).apply {
                    putExtra(DashboardActivity.EXTRA_KEYPASS, it)
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
