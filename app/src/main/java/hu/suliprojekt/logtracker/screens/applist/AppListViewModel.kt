package hu.suliprojekt.logtracker.screens.applist

import android.app.Application
import androidx.lifecycle.*
import hu.suliprojekt.logtracker.database.AppListDatabaseDao
import hu.suliprojekt.logtracker.database.AppListItem
import kotlinx.coroutines.launch

class AppListViewModel(dataSource: AppListDatabaseDao, application: Application) : AndroidViewModel(application) {

    val database = dataSource

    private val _appList = database.getAllAppNames()
    val appList
        get() = _appList

    private val _navigateToAppLogs = MutableLiveData<String>()
    val navigateToAppLogs: LiveData<String>
        get() = _navigateToAppLogs

    init {
//        initializeAppList()
    }


//    private fun initializeAppList() {
//        // if cant connect to server
//        viewModelScope.launch {
//            _appList.value = getAppNamesFromDatabase()
//        }
//    }

//    fun updateList(newList: List<String>) {
//        _appList.value = newList
//    }

    /////////////// Database functions ///////////////

//    private suspend fun getAppNamesFromDatabase() : List<AppListItem>? {
////        database.insert(AppListItem(0, "asd"))
//        return database.getAllAppNames()
//    }

    private suspend fun insert(app: AppListItem) {
        database.insert(app)
    }

    private suspend fun clear() {
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