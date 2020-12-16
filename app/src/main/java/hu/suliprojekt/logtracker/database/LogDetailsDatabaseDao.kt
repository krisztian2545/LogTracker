package hu.suliprojekt.logtracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDetailsDatabaseDao {

    @Insert
    suspend fun insert(log: LogDetails)

    @Query("SELECT * FROM log_detail_table WHERE app_name = :appName")
    fun getAppLogs(appName: String): LiveData<List<LogDetails>>

    @Query("DELETE FROM log_detail_table")
    suspend fun clear()

    @Query("DELETE FROM log_detail_table WHERE app_name = :appName")
    suspend fun clearAppLogs(appName: String)
}