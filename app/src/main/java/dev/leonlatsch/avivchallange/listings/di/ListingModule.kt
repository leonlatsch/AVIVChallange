package dev.leonlatsch.avivchallange.listings.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.data.remote.ListingApi
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingModule {

    @Provides
    @Singleton
    fun provideListingApi(retrofit: Retrofit): ListingApi = retrofit.create(ListingApi::class.java)

    @Binds
    abstract fun bindListingRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingRepository
}