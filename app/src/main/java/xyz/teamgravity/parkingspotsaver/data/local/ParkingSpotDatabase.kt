package xyz.teamgravity.parkingspotsaver.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.teamgravity.parkingspotsaver.data.model.ParkingSpotModel

@Database(
    entities = [ParkingSpotModel::class],
    version = ParkingSpotConst.VERSION
)
abstract class ParkingSpotDatabase : RoomDatabase() {

    abstract fun parkingSpotDao(): ParkingSpotDao
}