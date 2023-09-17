package dev.leonlatsch.avivchallange.listings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.view.ListingsScreenViewModel
import dev.leonlatsch.avivchallange.listings.view.ListingsScreenViewModelImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingBindingModule {

    @Binds
    abstract fun bindListingRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingRepository

    @Binds
    abstract fun bindListingsViewModel(listingsScreenViewModelImpl: ListingsScreenViewModelImpl): ListingsScreenViewModel
}