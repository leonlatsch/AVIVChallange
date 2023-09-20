package dev.leonlatsch.avivchallange.listings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.listings.data.remote.ListingApi
import retrofit2.Retrofit
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ListingModule {

    @Provides
    @Singleton
    fun provideListingApi(retrofit: Retrofit): ListingApi = retrofit.create(ListingApi::class.java)

    @Provides
    fun provideCurrencyFormat(): NumberFormat = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance("EUR")
    }
}