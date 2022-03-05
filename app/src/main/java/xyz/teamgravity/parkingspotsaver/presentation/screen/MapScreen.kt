package xyz.teamgravity.parkingspotsaver.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import xyz.teamgravity.parkingspotsaver.presentation.viewmodel.MapViewModel

@Composable
fun MapScreen() {

    val viewmodel = hiltViewModel<MapViewModel>()
    val scaffold = rememberScaffoldState()
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }

    Scaffold(
        scaffoldState = scaffold,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewmodel.onEvent(MapViewModel.MapEvent.ToggleFalloutMap)
                }
            ) {
                Icon(
                    imageVector = if (viewmodel.state.falloutMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn,
                    contentDescription = "FAB"
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewmodel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewmodel.onEvent(MapViewModel.MapEvent.MapLongClick(it))
            },
        ) {
            viewmodel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.latitude, spot.longitude),
                    title = "Parking spot ${spot.latitude} ${spot.longitude}",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewmodel.onEvent(MapViewModel.MapEvent.InfoWindowLongClick(spot))
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }
        }
    }
}