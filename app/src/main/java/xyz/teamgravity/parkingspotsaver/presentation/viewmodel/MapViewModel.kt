package xyz.teamgravity.parkingspotsaver.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xyz.teamgravity.parkingspotsaver.core.MapStyle
import xyz.teamgravity.parkingspotsaver.data.model.ParkingSpotModel
import xyz.teamgravity.parkingspotsaver.data.repository.ParkingSpotRepository
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repository.getAllParkingSpot().collectLatest { spots ->
                state = state.copy(
                    parkingSpots = spots
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.falloutMap) null else MapStyleOptions(MapStyle.FALLOUT)
                    ),
                    falloutMap = !state.falloutMap
                )
            }

            is MapEvent.MapLongClick -> {
                viewModelScope.launch {
                    repository.insertParkingSpotSync(
                        ParkingSpotModel(
                            latitude = event.latLng.latitude,
                            longitude = event.latLng.longitude
                        )
                    )
                }
            }

            is MapEvent.InfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteParkingSpotSync(event.spot)
                }
            }
        }
    }

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