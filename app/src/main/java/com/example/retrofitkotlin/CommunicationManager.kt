package com.example.retrofitkotlin

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class CommunicationManager {

    private var mCommunicationManager: CommunicationManager? = null

    fun getInstance(): CommunicationManager {
        if (mCommunicationManager == null) {
            mCommunicationManager = CommunicationManager()
        }
        return mCommunicationManager as CommunicationManager
    }


    fun getRetrofitInstance(head: Boolean): RetrofitAPI? {
        var api: RetrofitAPI? = null
        val url: String?
        try {
            url = "https://jsonplaceholder.typicode.com/"
            if (null != url) {
                val okHttpClient: OkHttpClient
                if (head) {
                    okHttpClient = OkHttpClient.Builder()
                        .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(object : Interceptor {
                            @Throws(IOException::class)
                            override fun intercept(chain: Interceptor.Chain): Response {
                                val request = chain.request().newBuilder()
                                    .addHeader("", "")
                                    .build()
                                return chain.proceed(request)
                            }
                        })
                        .build()
                } else {
                    okHttpClient = OkHttpClient.Builder()
                        .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .build()
                }
                val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(RetrofitAPI::class.java)

            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return api
    }

    fun getPostListReq(): Call<List<Post>>? {
        try {
            return getRetrofitInstance(false)?.postResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

}