package hu.suliprojekt.logtracker.screens.loglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import hu.suliprojekt.logtracker.database.LogDetails
import hu.suliprojekt.logtracker.database.LogDetailsDatabaseDao

class LogListViewModel(private val appName: String, dataSource: LogDetailsDatabaseDao, application: Application) : AndroidViewModel(application) {

    val database = dataSource

    private val _logList = database.getAppLogs(appName)
    val logList
        get() = _logList


    private suspend fun insert(log: LogDetails) {
        database.insert(log)
    }

    private suspend fun clear() {
        database.clearAppLogs(appName)
    }
}