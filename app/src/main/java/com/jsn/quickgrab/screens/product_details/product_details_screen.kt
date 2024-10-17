package com.jsn.quickgrab.screens.product_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jsn.quickgrab.R
import com.jsn.quickgrab.components.SpacerHeight
import com.jsn.quickgrab.ui.theme.DarkOrange
import com.jsn.quickgrab.ui.theme.TextColor_1

@Composable
fun ProductDetailsScreen(navHostController: NavHostController? = null) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.product_four),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth
        )
        HeaderSection(
            onBack = {
                navHostController?.navigateUp()
            },
            onShareButtonClick = {

            }
        )
        Box(
            modifier = Modifier
                .padding(top = 230.dp)
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(16.dp))

        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
                item {
                    ProductHeaderSection()
                }
            }
        }
    }
}

@Composable
fun HeaderSection(onBack: () -> Unit, onShareButtonClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
            }
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    onShareButtonClick.invoke()
                }
            )

        }
    )
}

@Composable
fun ProductHeaderSection() {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.product_name),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                color = TextColor_1
            )
        )
        SpacerHeight()
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string._599),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkOrange
                ),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            ProductCountButton(icon = Icons.Default.Add) {
                if (count > 0)
                    count--
            }
            Text(
                text = "$count",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            ProductCountButton(icon = Icons.Default.Add) {
                count++
            }

        }

    }
}

@Composable
fun ProductCountButton(icon: ImageVector, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(35.dp),
        border = BorderStroke(2.dp, DarkOrange),
        elevation = ButtonDefaults.buttonElevation(0.dp)

    ) {
        Icon(icon, contentDescription = "", tint = DarkOrange)
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ProductDetailsScreenPreview() {
    ProductDetailsScreen()
}