package com.submission.dicoding.core.di

import androidx.room.Room
import com.submission.dicoding.core.BuildConfig
import com.submission.dicoding.core.data.GamesRepository
import com.submission.dicoding.core.data.source.local.LocalDataSource
import com.submission.dicoding.core.data.source.local.room.GameDatabase
import com.submission.dicoding.core.data.source.remote.RemoteDataSource
import com.submission.dicoding.core.data.source.remote.network.ApiService
import com.submission.dicoding.core.domain.repository.IGamesRepository
import com.submission.dicoding.core.utils.Constant.CONNECTION_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    factory { get<GameDatabase>().gameScreenshotDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "GameDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    single<IGamesRepository> { GamesRepository(get(), get()) }
}