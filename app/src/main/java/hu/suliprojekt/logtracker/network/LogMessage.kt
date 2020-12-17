package hu.suliprojekt.logtracker.network

data class LogMessage(
    val appName: String,
    val message: String,
    val time: Long
)