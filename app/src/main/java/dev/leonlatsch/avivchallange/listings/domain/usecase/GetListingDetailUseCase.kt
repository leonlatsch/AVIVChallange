package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import javax.inject.Inject

class GetListingDetailUseCase @Inject constructor(
    private val listingRepository: ListingRepository,
) {
    suspend operator fun invoke(listingId: Int): Result<Listing> =
        listingRepository.getListingDetail(listingId)
}