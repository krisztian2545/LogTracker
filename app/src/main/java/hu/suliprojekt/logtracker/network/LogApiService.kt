package hu.suliprojekt.logtracker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

const val BASE_URL = "http://192.168.0.106:5000"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface LogApiService {

    @GET("api/log")
    suspend fun getAppNames(): List<String>

    @GET("api/log/{appName}")
    suspend fun getAppLogs(@Path("appName") appName: String): List<LogMessage>

}

object LogApi {
    val retrofitService: LogApiService by lazy {
        retrofit.create(LogApiService::class.java)
    }
}