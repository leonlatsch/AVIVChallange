package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import javax.inject.Inject

class LoadListingsUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    suspend operator fun invoke() {
        listingRepository.loadListings()
    }
}