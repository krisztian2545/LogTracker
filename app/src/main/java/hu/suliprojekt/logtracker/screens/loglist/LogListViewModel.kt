package hu.suliprojekt.logtracker.screens.loglist

import android.app.Application
import androidx.lifecycle.*
import hu.suliprojekt.logtracker.database.AppListItem
import hu.suliprojekt.logtracker.database.LogDetails
import hu.suliprojekt.logtracker.database.LogDetailsDatabaseDao
import hu.suliprojekt.logtracker.network.BASE_URL
import hu.suliprojekt.logtracker.network.LogApi
import hu.suliprojekt.logtracker.network.LogMessage
import kotlinx.coroutines.launch

class LogListViewModel(private val appName: String, dataSource: LogDetailsDatabaseDao, application: Application) : AndroidViewModel(application) {

    val database = dataSource

    private val _logList = database.getAppLogs(appName)
    val logList
        get() = _logList

    private val _isServerAvailable = MutableLiveData<Boolean>()
    val isServerAvailable: LiveData<Boolean>
        get() = _isServerAvailable

    init {
        refreshList()
    }

    private fun getLogListFromServer() {
        viewModelScope.launch {
            try {
                val listResult = LogApi.retrofitService.getAppLogs(appName)
                insertListToDB(listResult)
            } catch (e: Exception) {
                println("Failre: " + e.message)
                serverNotAvailable()
            }
        }
    }

    private suspend fun insertListToDB(list: List<LogMessage>?) {
        viewModelScope.launch {
            clearAppLogs()
            list?.forEach {
                insert(it.message, it.time)
            }
        }
    }

    private suspend fun insert(message: String, time: Long) {
        database.insert(LogDetails(0, appName, message, time))
    }

    private suspend fun clearAppLogs() {
        database.clearAppLogs(appName)
    }

    ////////////// Event handlers //////////////

    fun onRefreshButtonClicked() {
        refreshList()
    }

    private fun refreshList() {
        getLogListFromServer()
    }

    private fun serverNotAvailable() {
        _isServerAvailable.value = false
    }

    fun userNotified() {
        _isServerAvailable.value = true
    }

}