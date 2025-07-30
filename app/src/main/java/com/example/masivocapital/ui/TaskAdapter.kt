package com.example.masivocapital.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.masivocapital.databinding.ItemTaskBinding
import com.example.masivocapital.ui.model.TaskUiModel

class TaskAdapter : ListAdapter<TaskUiModel, TaskAdapter.VH>(Diff) {

    class VH(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TaskUiModel) = with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.description
            tvTime.text = item.relativeTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(getItem(position))

    private object Diff : DiffUtil.ItemCallback<TaskUiModel>() {
        override fun areItemsTheSame(o: TaskUiModel, n: TaskUiModel) = o.id == n.id
        override fun areContentsTheSame(o: TaskUiModel, n: TaskUiModel) = o == n
    }
}