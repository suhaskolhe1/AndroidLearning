package com.example.amphibians.data

import com.example.amphibians.network.AmphibianPhoto
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansDataRepository {
    suspend fun getAmphibianData(): List<AmphibianPhoto>
}

class NetworkAmphibianDataReposiory(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansDataRepository {
    override suspend fun getAmphibianData() = amphibiansApiService.getData()

}