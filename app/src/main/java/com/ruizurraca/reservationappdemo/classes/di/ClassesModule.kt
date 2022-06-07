package com.ruizurraca.reservationappdemo.classes.di

import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.classes.data.api.AimharderClassesApi
import com.ruizurraca.reservationappdemo.classes.data.repository.ClassesRepositoryImpl
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import com.ruizurraca.reservationappdemo.common.CustomCookieJar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassesModule {

    @Singleton
    @Provides
    @Named("interceptorClasses")
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    @Named("okHttpClasses")
    fun providesOkHttpClient(
        @Named("interceptorClasses") httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("commonCookieJar") customCookieJar: CustomCookieJar
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cookieJar(customCookieJar)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("retrofitClasses")
    fun provideRetrofit(@Named("okHttpClasses") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_BOX_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("aimharderApiClasses")
    fun provideAimharderLoginApi(@Named("retrofitClasses") retrofit: Retrofit): AimharderClassesApi =
        retrofit.create(AimharderClassesApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(@Named("aimharderApiClasses") aimharderClassesApi: AimharderClassesApi): ClassesRepository =
        ClassesRepositoryImpl(aimharderClassesApi)
}