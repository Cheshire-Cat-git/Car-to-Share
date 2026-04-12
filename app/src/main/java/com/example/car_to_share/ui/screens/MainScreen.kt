package com.example.car_to_share.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.car_to_share.ui.components.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    nearestDistance: String = "350 м",
    carCount: Int = 5,
    showToast: Boolean = false,
    selectedNavItem: Int = 0,
    onNavItemSelected: (Int) -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onFilterSelected: (String) -> Unit = {},
    onListToggle: () -> Unit = {},
    mapContent: @Composable BoxScope.() -> Unit = {}
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    val scope = rememberCoroutineScope()

    val defaultLocation = LatLng(55.751244, 37.618423)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        bottomBar = {
            DriveOrangeBottomNav(
                selectedItem = selectedNavItem,
                onItemSelected = onNavItemSelected
            )
        }
    ) { scaffoldPadding ->
        BottomSheetScaffold(
            modifier = Modifier.padding(scaffoldPadding),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 300.dp,
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetContainerColor = Color.White,
            sheetShadowElevation = 16.dp,
            sheetContent = {
                BottomSheetContent(
                    carCount = carCount,
                    nearestDistance = nearestDistance,
                    onListToggle = onListToggle
                )
            },
            containerColor = Color.White
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // ── 1. Google Maps layer ─────────────────────────────────────
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = uiSettings
                )


                // ── 5. Success toast (animated overlay) ───────────────────────
                AnimatedVisibility(
                    visible = showToast,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 72.dp, start = 16.dp, end = 16.dp)
                        .zIndex(10f)
                ) {
                    SuccessToast()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}
