package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAndLoadListingDetailUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    suspend operator fun invoke(listingId: Int): Flow<Listing> {
        listingRepository.loadListingDetail(listingId)
        return listingRepository.observeListingDetail(listingId)
    }
}