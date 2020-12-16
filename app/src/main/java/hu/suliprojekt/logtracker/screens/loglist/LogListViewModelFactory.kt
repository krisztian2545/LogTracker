package hu.suliprojekt.logtracker.screens.loglist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.suliprojekt.logtracker.database.LogDetailsDatabaseDao

class LogListViewModelFactory(private val appName: String, private val dataSource: LogDetailsDatabaseDao, private val application: Application) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogListViewModel::class.java)) {
            return LogListViewModel(appName, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}