package com.ruizurraca.reservationappdemo.login.di

import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.common.CustomCookieJar
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    @Named("interceptorLogin")
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    @Named("okHttpLogin")
    fun providesOkHttpClient(
        @Named("interceptorLogin") httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("commonCookieJar") customCookieJar: CustomCookieJar
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cookieJar(customCookieJar)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("retrofitLogin")
    fun provideRetrofit(@Named("okHttpLogin") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BuildConfig.BASE_LOGIN_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("aimharderApiLogin")
    fun provideAimharderLoginApi(@Named("retrofitLogin") retrofit: Retrofit): AimharderLoginApi =
        retrofit.create(AimharderLoginApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(@Named("aimharderApiLogin") aimharderLoginApi: AimharderLoginApi): LoginRepository =
        LoginRepositoryImpl(aimharderLoginApi)
}