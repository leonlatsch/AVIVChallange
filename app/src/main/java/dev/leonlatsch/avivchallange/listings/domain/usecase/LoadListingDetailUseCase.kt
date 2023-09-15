package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import javax.inject.Inject

class LoadListingDetailUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    suspend operator fun invoke(listingId: Int) {
        listingRepository.loadListingDetail(listingId)
    }
}