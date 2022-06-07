package com.ruizurraca.reservationappdemo.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    @Named("commonCookieJar")
    fun providesCustomCookieJar() = CustomCookieJar()
}