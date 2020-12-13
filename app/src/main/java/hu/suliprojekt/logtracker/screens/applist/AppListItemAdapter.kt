package hu.suliprojekt.logtracker.screens.applist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.suliprojekt.logtracker.R
import kotlinx.android.synthetic.main.text_item_view.view.*

class AppListItemAdapter : RecyclerView.Adapter<AppListItemAdapter.TextItemViewHolder>() {

    class TextItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false)
        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item
    }

    override fun getItemCount() = data.size

}