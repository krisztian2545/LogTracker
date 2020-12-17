package hu.suliprojekt.logtracker.screens.loglist

import android.app.Application
import androidx.lifecycle.*
import hu.suliprojekt.logtracker.database.LogDetails
import hu.suliprojekt.logtracker.database.LogDetailsDatabaseDao
import kotlinx.coroutines.launch

class LogListViewModel(private val appName: String, dataSource: LogDetailsDatabaseDao, application: Application) : AndroidViewModel(application) {

    val database = dataSource

    private val _logList = database.getAppLogs(appName)
    val logList
        get() = _logList


    init {
        refreshList()
    }


    private suspend fun insert(message: String, time: Long) {
        database.insert(LogDetails(0, appName, message, time))
    }

    private suspend fun clear() {
        database.clearAppLogs(appName)
    }

    ////////////// Event handlers //////////////

    fun onRefreshButtonClicked() {
        refreshList()

        //debug
        viewModelScope.launch {
            insert("mukodjjhdjdjjjkdfksdhfkweyrbfkjhsdbvkuaekuhvaskjdhvbkeuryvkjashdgvkjahdkuvcsakjdvhkajsdvkjsahdfkjsaygdkfsdjf", 64846)
        }
    }

    private fun refreshList() {
//        TODO(refresh list from internet)
    }

}