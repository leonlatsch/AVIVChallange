package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAndLoadListingsUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    suspend operator fun invoke(): Flow<List<Listing>> {
        listingRepository.loadListings()
        return listingRepository.observeListings()
    }
}
