package com.example.car_to_share.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_to_share.ui.theme.*

@Composable
fun BottomSheetContent(
    carCount: Int,
    nearestDistance: String,
    onListToggle: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 4.dp)
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Gray300)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Рядом с вами",
                    color = Gray800,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$carCount авто доступны",
                    color = Gray400,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Text(
                text = "Список",
                color = Brand500,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(onClick = onListToggle)
            )
        }

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(3) {
                CarCardPlaceholder()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            StatCard(
                value = "$carCount",
                label = "Свободных",
                valueColor = Gray800,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                value = nearestDistance,
                label = "Ближайший",
                valueColor = Brand500,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                value = "от 4₽",
                label = "За минуту",
                valueColor = Gray800,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CarCardPlaceholder() {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Gray100)
    )
}
