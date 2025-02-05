package com.jsn.quickgrab.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jsn.quickgrab.R
import com.jsn.quickgrab.data.remote.model.Category
import com.jsn.quickgrab.data.remote.model.PopularProducts
import com.jsn.quickgrab.data.remote.model.Product
import com.jsn.quickgrab.data.remote.model.Rooms
import com.jsn.quickgrab.data.remote.model.categoryList
import com.jsn.quickgrab.data.remote.model.roomList
import com.jsn.quickgrab.navigation.ProductDetails
import com.jsn.quickgrab.ui.components.SpacerHeight
import com.jsn.quickgrab.ui.components.SpacerWidth
import com.jsn.quickgrab.ui.screens.ProductsViewModel
import com.jsn.quickgrab.ui.theme.DarkOrange
import com.jsn.quickgrab.ui.theme.LightGray_1
import com.jsn.quickgrab.ui.theme.TextColor_1

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController? = null) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
//            .background(Color.Cyan)
            .background(Color.White)
    ) {

        item {
            Header()
            CustomTextField(text = text, onValueChange = { newValue ->
                text = newValue
            })
            SpacerHeight(20.dp)
            CategoryRow()
            SpacerHeight(20.dp)
            PopularRow {
                navHostController?.navigate(ProductDetails)
            }
            BannerRow()
            Rooms()
        }

    }
}

@Composable
fun Header(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.heading_text), style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight.W600, color = Color.Black
            )
        )
        IconButton(
            onClick = onClick, modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "",
                tint = Color.Unspecified
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { newValue -> onValueChange(newValue) },
//        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
//        shape = RoundedCornerShape(8.dp), //Rounded corner shape is not working
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder), style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = LightGray_1
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                tint = LightGray_1
            )
        },
        modifier = modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .border(
                1.dp,
                LightGray_1,
                RoundedCornerShape(8.dp)
            ), // Rounded corner shape is working here
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun CategoryRow() {
    Column {
        CommonTitle(title = stringResource(id = R.string.categories))
        SpacerHeight(20.dp)
        LazyRow {
            items(categoryList, key = { it.id }) {
                CategoryEachRow(category = it)
            }
        }
    }
}

@Composable
fun CommonTitle(title: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TextButton(onClick = onClick) {
            Text(
                text = stringResource(id = R.string.see_all),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = DarkOrange
                )
            )
            SpacerWidth(3.dp)
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = DarkOrange,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun CategoryEachRow(category: Category) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .background(category.color, RoundedCornerShape(8.dp))
            .width(140.dp)
            .height(80.dp),
    ) {
        Text(
            text = category.title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterStart)
        )
        Image(
            painter = painterResource(id = category.image),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 5.dp)
                .align(BottomEnd)
        )
    }
}

@ExperimentalLayoutApi
@Composable
fun PopularRow(viewModel: ProductsViewModel = hiltViewModel(), onClick: () -> Unit) {
    Column {
        val products by viewModel.product.collectAsState(initial = emptyList())

        LaunchedEffect(Unit) {
            viewModel.getProducts()
        }
        CommonTitle(title = stringResource(id = R.string.popular))
        SpacerHeight()
        /* FLOWROW -> I1 I2 I3
                      I4 I5    (as there ar not enough space on the up so it wrap the next items to the next row) //It work's like grid but not scrollable
        */
//        FlowRow(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(products) {
                PopularEachRow(data = it) {
                    onClick()
                }
            }
        }
//            popularProductList.forEach {
//                PopularEachRow(data = it) {
//                    onClick()
//                }
//            }
//        }

    }
}

@Composable
fun PopularEachRowWithLocalData(
    data: PopularProducts,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(vertical = 5.dp)
            .clickable { onClick() },

        ) {
        Box {
            Image(
                painter = painterResource(id = data.image), contentDescription = "",
                modifier = Modifier
                    .width(141.dp)
                    .height(149.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.wishlist), contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(32.dp)
                    .align(TopEnd),
                tint = Color.Unspecified
            )
        }
        SpacerHeight(5.dp)
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Start)
                .width(141.dp),
//            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 8.dp, bottomStart = 8.dp)

        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                Text(
                    text = data.title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = data.price,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun PopularEachRow(
    data: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(vertical = 5.dp)
            .clickable { onClick() },

        ) {
        Box {
//            Image(
//                painter = painterResource(id = data.image), contentDescription = "",
//                modifier = Modifier
//                    .width(141.dp)
//                    .height(149.dp)
//            )
            AsyncImage(
                modifier = Modifier
                    .width(141.dp)
                    .height(149.dp),
                contentScale = ContentScale.FillBounds,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.image)
                    .crossfade(true)
                    // .size(141, 149)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
            )
            Icon(
                painter = painterResource(id = R.drawable.wishlist), contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(32.dp)
                    .align(TopEnd),
                tint = Color.Unspecified
            )
        }
        SpacerHeight(5.dp)
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Start)
                .width(141.dp),
//            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 8.dp, bottomStart = 8.dp)

        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                Text(
                    text = data.title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = data.price,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun BannerRow() {
    Image(
        painter = painterResource(id = R.drawable.banner),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(113.dp),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Rooms() {
    Column {
        Text(
            text = stringResource(id = R.string.rooms),
            style = TextStyle(
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color.Black
            )
        )
        SpacerHeight()
        Text(
            text = stringResource(id = R.string.room_des),
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = LightGray_1
            )
        )
        SpacerHeight()
        LazyRow {
            items(roomList, key = {
                it.id
            }) {
                RoomSection(rooms = it)
            }
        }
    }

}

@Composable
fun RoomSection(rooms: Rooms) {
    Box(modifier = Modifier.padding(end = 15.dp)) {
        Image(
            painter = painterResource(id = rooms.image),
            contentDescription = null,
            modifier = Modifier
                .width(127.dp)
                .height(195.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = rooms.title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = TextColor_1
            ),
            modifier = Modifier
                .width(100.dp)
                .padding(20.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}