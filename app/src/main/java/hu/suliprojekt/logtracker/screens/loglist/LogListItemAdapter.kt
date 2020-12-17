package hu.suliprojekt.logtracker.screens.loglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.suliprojekt.logtracker.database.LogDetails
import hu.suliprojekt.logtracker.databinding.LogDetailsViewBinding

class LogListItemAdapter : ListAdapter<LogDetails, LogListItemAdapter.LogDetailsViewHolder>(LogListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogDetailsViewHolder {
        return LogDetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LogDetailsViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item!!)
    }


    class LogDetailsViewHolder private constructor(val binding: LogDetailsViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LogDetails) {
            binding.logDetails = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): LogDetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LogDetailsViewBinding.inflate(layoutInflater, parent, false)
                return LogDetailsViewHolder(binding)
            }
        }
    }
}

class LogListDiffCallback : DiffUtil.ItemCallback<LogDetails>() {

    override fun areItemsTheSame(oldItem: LogDetails, newItem: LogDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LogDetails, newItem: LogDetails): Boolean {
        return (oldItem.message == newItem.message)
                && (oldItem.time == newItem.time)
    }

}