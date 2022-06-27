package com.ruizurraca.reservationappdemo.box.di

import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.box.data.api.AimharderBoxesApi
import com.ruizurraca.reservationappdemo.box.data.repository.BoxesRepositoryImpl
import com.ruizurraca.reservationappdemo.box.domain.repository.BoxesRepository
import com.ruizurraca.reservationappdemo.common.CustomCookieJar
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
object BoxesModule {

    @Singleton
    @Provides
    @Named("interceptorBoxes")
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    @Named("okHttpBoxes")
    fun providesOkHttpClient(
        @Named("interceptorBoxes") httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("commonCookieJar") customCookieJar: CustomCookieJar
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cookieJar(customCookieJar)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named("retrofitBoxes")
    fun provideRetrofit(@Named("okHttpBoxes") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BuildConfig.BASE_BOX_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("aimharderApiBoxes")
    fun provideAimharderLoginApi(@Named("retrofitBoxes") retrofit: Retrofit): AimharderBoxesApi =
        retrofit.create(AimharderBoxesApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(@Named("aimharderApiBoxes") aimharderBoxesApi: AimharderBoxesApi): BoxesRepository =
        BoxesRepositoryImpl(aimharderBoxesApi)
}
