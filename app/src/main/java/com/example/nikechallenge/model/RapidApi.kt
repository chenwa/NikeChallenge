package com.example.nikechallenge.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.nikechallenge.BuildConfig
import com.example.nikechallenge.NikeApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RapidApi {

    // Assume we do not need to hide our API Key
    @GET("define")
    @Headers(
        value = [
            "x-rapidapi-host:mashape-community-urban-dictionary.p.rapidapi.com",
            "x-rapidapi-key:e804ce0e47msh24b29c79665664ep190a81jsnb661c103d808"]
    )

    fun getResponse(@Query("term") input: String): Call<DefinitionResponse>

    companion object {
        // Use retrofit to make RapidAPI call
        fun initRetrofit(): RapidApi {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RapidApi::class.java)
        }

        val client: OkHttpClient by lazy {
            initClient()
        }

        private fun initClient(): OkHttpClient {
            val client = OkHttpClient.Builder()

            // Logger for debugging purposes
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            if (BuildConfig.DEBUG) {
                client.addInterceptor(logger)
            }
            client.addInterceptor {
                var request = it.request()
                request = if (isOffLine())
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 2 * 60
                    ).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 3 * 60
                    ).build()
                it.proceed(request)
            }
                .cache(cacheConfig())
                .build()

            return client.build()
        }

        // Checks for internet connectivity
        private fun isOffLine(): Boolean {
            val connectiviyManager: ConnectivityManager = NikeApp.nikeApp
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            var isConnected = false
            connectiviyManager.activeNetworkInfo?.let {
                isConnected = it.isConnected
            }

            return isConnected
        }

        // Set cache size
        private fun cacheConfig(): Cache {
            val cacheSize = (1024 * 1024).toLong()
            return Cache(NikeApp.nikeApp.cacheDir, cacheSize)
        }
    }
}