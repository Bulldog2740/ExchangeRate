package com.web.exchangerate.di.network

import android.content.Context
import com.web.exchangerate.BuildConfig
import com.web.exchangerate.remote.NBUApiService
import com.web.exchangerate.remote.PBApiService

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 *Created by Yehor Kudimov on 07.01.2021.
 */
fun networkModule() = module {

    single {
        val okHttpClient = createOkHttpClient(androidApplication())
        val retrofitPB =
            getBaseRetrofit(okHttpClient, androidApplication(), BuildConfig.API_BASE_URL_PB)
        retrofitPB.create(PBApiService::class.java)
    }
    single {
        val okHttpClient2 = createOkHttpClient(androidApplication())
        val retrofitNBU =
            getBaseRetrofit(okHttpClient2, androidApplication(), BuildConfig.API_BASE_URL_NBU)
        retrofitNBU.create(NBUApiService::class.java)

    }
}

fun createOkHttpClient(context: Context): OkHttpClient.Builder {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        /*.authenticator(TokenRefreshAuthenticator(context))*/
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder()
                    /*.headers(getHeaders())*/
                    .build()
                return chain.proceed(request)
            }
        })
}

fun getBaseRetrofit(okHttpClient: OkHttpClient.Builder, context: Context, url: String): Retrofit {

    val iterator = okHttpClient.interceptors().iterator()
    while (iterator.hasNext()) {
        val interceptor = iterator.next()
        if (interceptor is MiddlewareInterceptor)
            iterator.remove()
    }

    okHttpClient.addInterceptor(MiddlewareInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(AppGsonConvertorFactory.create(context))
        .baseUrl(url)
        .client(okHttpClient.build())
        .build()
}

