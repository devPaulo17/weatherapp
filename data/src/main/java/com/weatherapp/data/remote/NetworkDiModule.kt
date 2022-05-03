package com.weatherapp.data.remote

import com.weatherapp.data.remote.retrofit.SearchApiService
import com.weatherapp.data.remote.retrofit.WeatherApiService
import com.weatherapp.data.remote.search.RemoteSearchDataSourceImpl
import com.weatherapp.data.remote.weather.RemoteWeatherDataSourceImpl
import com.weatherapp.domain.repositories.search.RemoteSearchDataSource
import com.weatherapp.domain.repositories.weather.RemoteWeatherDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    factory { provideWeatherApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

    factory<RemoteSearchDataSource> {
        RemoteSearchDataSourceImpl(get())
    }

    factory<RemoteWeatherDataSource> {
        RemoteWeatherDataSourceImpl(get())
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://www.metaweather.com/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun provideForecastApi(retrofit: Retrofit): SearchApiService =
    retrofit.create(SearchApiService::class.java)

fun provideWeatherApi(retrofit: Retrofit): WeatherApiService =
    retrofit.create(WeatherApiService::class.java)


