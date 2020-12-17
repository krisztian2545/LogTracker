package hu.suliprojekt.logtracker.screens.applist

import android.app.Application
import androidx.lifecycle.*
import hu.suliprojekt.logtracker.database.AppListDatabaseDao
import hu.suliprojekt.logtracker.database.AppListItem
import hu.suliprojekt.logtracker.network.LogApi
import hu.suliprojekt.logtracker.network.LogMessage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppListViewModel(dataSource: AppListDatabaseDao, application: Application) : AndroidViewModel(application) {

    val database = dataSource

    private val _appList = database.getAllAppNames()
    val appList
        get() = _appList

    private val _navigateToAppLogs = MutableLiveData<String>()
    val navigateToAppLogs: LiveData<String>
        get() = _navigateToAppLogs

    private val _isServerAvailable = MutableLiveData<Boolean>()
    val isServerAvailable: LiveData<Boolean>
        get() = _isServerAvailable

    init {
        initializeAppList()
    }


    private fun initializeAppList() {
        // if cant connect to server
        getAppListFromServer()
    }

    private fun getAppListFromServer() {
        viewModelScope.launch {
            try {
                val listResult = LogApi.retrofitService.getAppNames()
                insertListToDB(listResult)
            } catch (e: Exception) {
                println("Failre: " + e.message)
                serverNotAvailable()
            }
        }
    }

//    fun updateList(newList: List<String>) {
//        _appList.value = newList
//    }

    private suspend fun insertListToDB(list: List<String>?) {
        viewModelScope.launch {
            clearTable()
            list?.forEach {
                insert(AppListItem(0, it))
            }
        }
    }

    fun onRefreshButtonClicked() {
        refreshList()
    }

    private fun refreshList() {
        getAppListFromServer()
    }

    private fun serverNotAvailable() {
        _isServerAvailable.value = false
    }

    fun userNotified() {
        _isServerAvailable.value = true
    }

    /////////////// Database functions ///////////////

    private suspend fun insert(app: AppListItem) {
        database.insert(app)
    }

    private suspend fun clearTable() {
        database.clear()
    }

    ////////////// Event handlers //////////////

    fun onAppListItemClicked(name: String) {
        _navigateToAppLogs.value = name
    }

    fun onAppLogsNavigated() {
        _navigateToAppLogs.value = null
    }
}