package xyz.teamgravity.parkingspotsaver.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.teamgravity.parkingspotsaver.data.local.ParkingSpotConst

@Entity(tableName = ParkingSpotConst.TABLE_PARKING_SPOT)
data class ParkingSpotModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val latitude: Double,
    val longitude: Double
)
