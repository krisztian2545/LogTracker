package hu.suliprojekt.logtracker.screens.loglist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.suliprojekt.logtracker.convertLongToDateString
import hu.suliprojekt.logtracker.database.LogDetails

@BindingAdapter("timeFormatted")
fun TextView.setTimeFormatted(item: LogDetails) {
    text = convertLongToDateString(item.time)
}