package com.example.car_to_share.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.car_to_share.ui.theme.*

@Composable
fun HeaderCard(
    modifier: Modifier = Modifier,
    onNotificationsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFilterSelected: (String) -> Unit
) {
    Column(
        modifier = modifier.background(Color.White)
    ) {
        HeaderGreetingRow(onNotificationsClick = onNotificationsClick)
        SearchBar(onSearchClick = onSearchClick)
        FilterChipsRow(onFilterSelected = onFilterSelected)
    }
}

@Composable
fun HeaderGreetingRow(
    modifier: Modifier = Modifier,
    userName: String = "Алексей М.",
    greeting: String = "Добрый день",
    onNotificationsClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Gray200)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Gray400,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = greeting,
                color = Gray400,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = userName,
                color = Gray800,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Gray100)
                .clickable(onClick = onNotificationsClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Уведомления",
                tint = Gray600,
                modifier = Modifier.size(20.dp)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-4).dp, y = 4.dp)
                    .clip(CircleShape)
                    .background(Brand500)
                    .border(2.dp, Color.White, CircleShape)
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = {
                    Text("Куда поедем?", color = Gray400, fontSize = 14.sp)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Gray400,
                        modifier = Modifier.size(18.dp)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Gray100,
                    focusedContainerColor = Gray100,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Brand500.copy(alpha = 0.3f),
                    cursorColor = Brand500
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 6.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Brand500)
                    .clickable(onClick = onSearchClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun FilterChipsRow(
    modifier: Modifier = Modifier,
    onFilterSelected: (String) -> Unit
) {
    val filters = listOf("Все", "Эконом", "Комфорт", "Электро", "Бизнес")
    var selectedFilter by remember { mutableStateOf("Все") }

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { filter ->
            val isSelected = filter == selectedFilter
            FilterChip(
                selected = isSelected,
                onClick = {
                    selectedFilter = filter
                    onFilterSelected(filter)
                },
                label = {
                    Text(
                        text = filter,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Brand500,
                    selectedLabelColor = Color.White,
                    containerColor = Gray100,
                    labelColor = Gray600
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    selectedBorderColor = Color.Transparent,
                    borderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(50)
            )
        }
    }
}
