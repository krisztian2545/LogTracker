package hu.suliprojekt.logtracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppListDatabaseDao {

    @Insert
    fun insert(app: AppListItem)

    @Query("SELECT * FROM app_list_table")
    fun getAllAppNames(): LiveData<List<AppListItem>>

    @Query("DELETE FROM app_list_table")
    fun clear()

}