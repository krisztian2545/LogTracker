package hu.suliprojekt.logtracker.screens.applist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppListViewModel(givenAppList: List<String>) : ViewModel() {

    private val _appList = MutableLiveData<List<String>>()
    val appList: LiveData<List<String>>
        get() = _appList

    init {
        _appList.value = givenAppList
    }

    fun updateList(newList: List<String>) {
        _appList.value = newList
    }
}