package com.example.car_to_share.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
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

@Composable
fun MapControls(
    modifier: Modifier = Modifier,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onCenter: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MapControlButton(icon = Icons.Default.Add, description = "Приблизить", onClick = onZoomIn)
        MapControlButton(icon = Icons.Default.Remove, description = "Отдалить", onClick = onZoomOut)
        MapControlButton(
            icon = Icons.Default.MyLocation,
            description = "Моё местоположение",
            iconTint = Brand500,
            onClick = onCenter
        )
    }
}

@Composable
fun MapControlButton(
    icon: ImageVector,
    description: String,
    iconTint: Color = Gray700,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun DistanceBadge(
    distance: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Brand50),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Brand500,
                modifier = Modifier.size(16.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "Ближайший",
                color = Gray400,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = distance,
                color = Gray800,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
