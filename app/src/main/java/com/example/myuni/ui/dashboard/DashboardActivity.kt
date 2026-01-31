package com.example.myuni.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myuni.databinding.ActivityDashboardBinding
import com.example.myuni.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEYPASS = "extra_keypass"
    }

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keypass = intent.getStringExtra(EXTRA_KEYPASS)
            ?: throw IllegalStateException("Keypass missing")

        setupRecycler()
        observeViewModel()
        viewModel.loadDashboard(keypass)
    }

    private fun setupRecycler() {
        adapter = EntityAdapter { entity ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_PROPERTY1, entity.property1)
                putExtra(DetailsActivity.EXTRA_PROPERTY2, entity.property2)
                putExtra(DetailsActivity.EXTRA_DESCRIPTION, entity.description)
            }
            startActivity(intent)
        }
        binding.rvEntities.layoutManager = LinearLayoutManager(this)
        binding.rvEntities.adapter = adapter
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
        viewModel.entities.observe(this) { list ->
            adapter.submitList(list)
        }
    }
}
