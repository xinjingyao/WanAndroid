package com.mg.axechen.wanandroid.network

import com.example.kotlindemo.network.converter.GsonConverterFactory
import com.mg.axechen.wanandroid.network.interceptor.AddCookieInterceptor
import com.mg.axechen.wanandroid.network.interceptor.GetCookieInterceptor
import network.request.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by AxeChen on 2018/3/19.
 *
 * 网络请求管理类
 */
class RetrofitHelper private constructor() {

    companion object {
        var mManger: RetrofitHelper? = null
        var mRetrofit: retrofit2.Retrofit? = null
        var mRequest: Request? = null

        // 初始化NetWorkManager单例
        fun getInstance(): RetrofitHelper {
            if (mManger == null) {
                synchronized(RetrofitHelper::class) {
                    if (mManger == null) {
                        mManger = RetrofitHelper()
                    }
                }
            }
            return mManger!!
        }
    }

    fun getRequest(): Request? {
        if (mRequest == null) {
            synchronized(Request::class) {
                mRequest = mRetrofit?.create(Request::class.java)
            }
        }
        return mRequest
    }

    fun init() {

        // 初始化OKHTTP
        val client: okhttp3.OkHttpClient.Builder = okhttp3.OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            addInterceptor(AddCookieInterceptor())
            addInterceptor(GetCookieInterceptor())
        }

        // 初始化Retrofit
        mRetrofit = retrofit2.Retrofit.Builder().apply {
            client(client.build())
            baseUrl(Request.HOST)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())


        }.build()
    }


}