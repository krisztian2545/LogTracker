package hu.suliprojekt.logtracker.screens.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppListViewModelFactory(private val givenAppList: List<String>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppListViewModel::class.java)) {
            return AppListViewModel(givenAppList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}