package xyz.teamgravity.parkingspotsaver.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import xyz.teamgravity.parkingspotsaver.data.local.ParkingSpotDao
import xyz.teamgravity.parkingspotsaver.data.model.ParkingSpotModel

class ParkingSpotRepository(
    private val dao: ParkingSpotDao
) {

    ///////////////////////////////////////////////////////////////////////////
    // Insert
    ///////////////////////////////////////////////////////////////////////////

    suspend fun insertParkingSpotSync(spot: ParkingSpotModel) {
        withContext(Dispatchers.IO) {
            dao.insertParkingSpot(spot)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Delete
    ///////////////////////////////////////////////////////////////////////////

    suspend fun deleteParkingSpotSync(spot: ParkingSpotModel) {
        withContext(Dispatchers.IO) {
            dao.deleteParkingSpot(spot)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get
    ///////////////////////////////////////////////////////////////////////////

    fun getAllParkingSpot(): Flow<List<ParkingSpotModel>> {
        return dao.getAllParkingSpot()
    }
}