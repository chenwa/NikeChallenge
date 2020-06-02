package com.example.nikechallenge

import com.example.nikechallenge.model.data.DefinitionResponse
import com.example.nikechallenge.model.network.IRapidApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Thread.sleep

/**
 * Tests to see if the RapidAPI call works
 */
class ApiUnitTest {
    @Test
    fun randomJoke_isCorrect() {
        var result: DefinitionResponse? = null

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(IRapidApi::class.java)

        retrofit.getResponse("test")
            .enqueue(object : Callback<DefinitionResponse> {
                override fun onFailure(call: Call<DefinitionResponse>, t: Throwable) {
                    t.printStackTrace()
                    fail()
                }
                override fun onResponse(call: Call<DefinitionResponse>,
                                        response: Response<DefinitionResponse>
                ) {
                    result = response.body()!!
                }
            }
        )
        sleep(1_000)
        assertNotNull(result)
    }
}
