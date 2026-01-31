package com.example.myuni.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myuni.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PROPERTY1 = "extra_property1"
        const val EXTRA_PROPERTY2 = "extra_property2"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val property1 = intent.getStringExtra(EXTRA_PROPERTY1).orEmpty()
        val property2 = intent.getStringExtra(EXTRA_PROPERTY2).orEmpty()
        val description = intent.getStringExtra(EXTRA_DESCRIPTION).orEmpty()

        binding.tvProperty1.text = property1
        binding.tvProperty2.text = property2
        binding.tvDescription.text = description
    }
}
