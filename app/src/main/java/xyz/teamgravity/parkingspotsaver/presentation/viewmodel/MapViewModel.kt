package xyz.teamgravity.parkingspotsaver.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.teamgravity.parkingspotsaver.data.model.ParkingSpotModel
import xyz.teamgravity.parkingspotsaver.data.repository.ParkingSpotRepository
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
) : ViewModel() {


    sealed class MapEvent {
        object ToggleFalloutMap : MapEvent()
        data class MapLongClick(val latLng: LatLng) : MapEvent()
        data class InfoWindowLongClick(val spot: ParkingSpotModel) : MapEvent()
    }

    data class MapState(
        val properties: MapProperties = MapProperties(),
        val parkingSpots: List<ParkingSpotModel> = emptyList(),
        val falloutMap: Boolean = false
    )
}