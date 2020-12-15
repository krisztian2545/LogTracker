package hu.suliprojekt.logtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LogDetails::class], version = 1, exportSchema = false)
abstract class LogDetailsDatabase : RoomDatabase() {

    abstract val logDetailsDatabaseDao: LogDetailsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: LogDetailsDatabase? = null

        fun getInstance(context: Context): LogDetailsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LogDetailsDatabase::class.java,
                        "log_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}