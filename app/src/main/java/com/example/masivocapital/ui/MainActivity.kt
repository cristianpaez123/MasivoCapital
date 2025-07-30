package com.example.masivocapital.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.masivocapital.R
import com.example.masivocapital.databinding.ActivityMainBinding
import com.example.masivocapital.databinding.DialogAddTaskBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        // RecyclerView
        binding.rvTasks.adapter = adapter

        // FAB
        binding.fabAdd.setOnClickListener { showAddDialog() }

        // State collector
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest(adapter::submitList)
        }
    }

    private fun showAddDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.add_task)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                val title = dialogBinding.etTitle.text?.toString().orEmpty().trim()
                val desc = dialogBinding.etDesc.text?.toString().orEmpty().trim()
                if (title.isNotEmpty()) viewModel.onAddClicked(title, desc)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
}
