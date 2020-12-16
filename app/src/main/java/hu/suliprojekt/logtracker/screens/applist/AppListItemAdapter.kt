package hu.suliprojekt.logtracker.screens.applist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.suliprojekt.logtracker.database.AppListItem
import hu.suliprojekt.logtracker.databinding.TextItemViewBinding

class AppListItemAdapter(val clickListener: AppListItemListener) : ListAdapter<AppListItem, AppListItemAdapter.TextItemViewHolder>(AppListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item!!, clickListener)
    }

    class TextItemViewHolder private constructor(val binding: TextItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AppListItem, clickListener: AppListItemListener) {
            binding.appListItem = item
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

class AppListDiffCallback : DiffUtil.ItemCallback<AppListItem>() {

    override fun areItemsTheSame(oldItem: AppListItem, newItem: AppListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AppListItem, newItem: AppListItem): Boolean {
        return oldItem.appName == newItem.appName
    }

}

class AppListItemListener(val clickListener: (appName: String) -> Unit) {
    fun onClick(appListItem: AppListItem) = clickListener(appListItem.appName)
}
