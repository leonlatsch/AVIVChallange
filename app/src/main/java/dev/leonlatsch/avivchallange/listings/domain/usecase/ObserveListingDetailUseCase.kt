package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveListingDetailUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    operator fun invoke(listingId: Int): Flow<Listing> =
        listingRepository.observeListingDetail(listingId)
}