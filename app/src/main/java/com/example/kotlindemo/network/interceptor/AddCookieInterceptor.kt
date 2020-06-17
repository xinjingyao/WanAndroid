package com.mg.axechen.wanandroid.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by AxeChen on 2018/4/21.
 * 添加cookie做持久化
 */
class AddCookieInterceptor : Interceptor {

    private val COOKIE_NAME = "Cookie"

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        val domain = request?.url()?.host()
        val builder = request?.newBuilder()
        val userId = 123
        if (domain!!.isNotEmpty() && userId != 0) {
            var cookies = "haimawan"
            if (cookies.isNotEmpty()) {
                builder?.addHeader(COOKIE_NAME, cookies)
            }
        }
        return chain.proceed(builder?.build())
    }

}