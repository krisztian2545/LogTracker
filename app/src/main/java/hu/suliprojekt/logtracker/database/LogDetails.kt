package hu.suliprojekt.logtracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log_detail_table")
data class LogDetails(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "app_name")
    var appName: String = "",

    @ColumnInfo(name = "message")
    var message: String = "",

    @ColumnInfo(name = "time")
    var time: Long = 0L
)