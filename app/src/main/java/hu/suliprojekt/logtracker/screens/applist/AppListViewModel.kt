package hu.suliprojekt.logtracker.screens.applist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppListViewModel(givenAppList: List<String>) : ViewModel() {

    private val _appList = MutableLiveData<List<String>>()
    val appList
        get() = _appList

    private val _navigateToAppLogs = MutableLiveData<String>()
    val navigateToAppLogs: LiveData<String>
        get() = _navigateToAppLogs

    init {
        _appList.value = givenAppList
    }

    fun updateList(newList: List<String>) {
        _appList.value = newList
    }

    fun onAppListItemClicked(name: String) {
        _navigateToAppLogs.value = name
    }

    fun onAppLogsNavigated() {
        _navigateToAppLogs.value = null
    }
}