package com.ruizurraca.reservationappdemo.login.di

import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.login.data.api.AimharderLoginApi
import com.ruizurraca.reservationappdemo.login.data.repository.LoginRepositoryImpl
import com.ruizurraca.reservationappdemo.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BuildConfig.BASE_LOGIN_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideAimharderLoginApi(retrofit: Retrofit): AimharderLoginApi =
        retrofit.create(AimharderLoginApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(aimharderLoginApi: AimharderLoginApi): LoginRepository =
        LoginRepositoryImpl(aimharderLoginApi)
}