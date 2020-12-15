package hu.suliprojekt.logtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppListItem::class], version = 1, exportSchema = false)
abstract class AppListDatabase : RoomDatabase(){

    abstract val appListDatabaseDao: AppListDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AppListDatabase? = null

        fun getInstance(context: Context): AppListDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppListDatabase::class.java,
                        "app_name_history_database"
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