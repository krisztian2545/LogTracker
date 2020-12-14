package hu.suliprojekt.logtracker.screens.applist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.suliprojekt.logtracker.databinding.TextItemViewBinding

class AppListItemAdapter(val clickListener: AppListItemListener) : ListAdapter<String, AppListItemAdapter.TextItemViewHolder>(AppListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item!!, clickListener)
    }

    class TextItemViewHolder private constructor(val binding: TextItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, clickListener: AppListItemListener) {
            binding.appName = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextItemViewBinding.inflate(layoutInflater, parent, false)
                return TextItemViewHolder(binding)
            }
        }
    }

}

class AppListDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

class AppListItemListener(val clickListener: (appName: String) -> Unit) {
    fun onClick(appName: String) = clickListener(appName)
}
