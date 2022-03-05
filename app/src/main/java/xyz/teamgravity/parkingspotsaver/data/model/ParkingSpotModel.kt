package xyz.teamgravity.parkingspotsaver.data.model

import androidx.room.PrimaryKey


data class ParkingSpotModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val latitude: Double,
    val longitude: Double
)
