package com.example.car_to_share.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_to_share.ui.theme.*

data class NavItem(val label: String, val icon: ImageVector)

@Composable
fun DriveOrangeBottomNav(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        NavItem("Карта", Icons.Default.LocationOn),
        NavItem("Каталог", Icons.Default.Menu),
        NavItem("Забронировать", Icons.Default.Add),
        NavItem("Поездки", Icons.Default.ShoppingCart),
        NavItem("Профиль", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            if (index == 2) {
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .offset(y = (-6).dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Brand500)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    ambientColor = Brand500.copy(alpha = 0.3f),
                                    spotColor = Brand500.copy(alpha = 0.3f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = item.label,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Brand500,
                        selectedTextColor = Brand500,
                        unselectedIconColor = Gray400,
                        unselectedTextColor = Gray400,
                        indicatorColor = Color.Transparent
                    )
                )
            } else {
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Brand500,
                        selectedTextColor = Brand500,
                        unselectedIconColor = Gray400,
                        unselectedTextColor = Gray400,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
