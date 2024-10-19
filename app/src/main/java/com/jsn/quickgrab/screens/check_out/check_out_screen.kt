package com.jsn.quickgrab.screens.check_out

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.jsn.quickgrab.components.SpacerWidth
import com.jsn.quickgrab.data.ShoppingBag
import com.jsn.quickgrab.data.shoppingList
import com.jsn.quickgrab.screens.product_details.ProductCountButton
import com.jsn.quickgrab.screens.product_details.RecommendedProduct
import com.jsn.quickgrab.ui.theme.DarkOrange
import com.jsn.quickgrab.ui.theme.LightGray_1
import com.jsn.quickgrab.ui.theme.LineColor
import com.jsn.quickgrab.ui.theme.TextColor_1

@Composable
fun CheckOutScreen(navHostController: NavHostController? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            IconButton(
                onClick = {
                    navHostController?.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
            Text(
                text = stringResource(id = R.string.my_shoping_bag),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                ),
                modifier = Modifier.padding(vertical = 15.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 80.dp)
            ) {
                items(shoppingList) {
                    ShoppingEachRow(it)
                }

                item {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        color = LineColor
                    )
                    SpacerHeight()
                    RecommendedProduct()
                }
            }
        }
        CheckOutRow(modifier = Modifier.align(BottomCenter))
    }

}

@Composable
fun ShoppingEachRow(data: ShoppingBag) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row {
            Image(
                painter = painterResource(id = data.icon),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .width(80.dp)
                    .height(80.dp)
                    .align(CenterVertically)

            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = data.title,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = TextColor_1
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.White, CircleShape)
                            .border(1.dp, DarkOrange, CircleShape)
                            .size(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier.size(10.dp)
                        )
                    }

                }

                Text(
                    text = "${data.qty} Qty",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        ProductCountButton(icon = Icons.Default.Add) {
                            if (count > 0)
                                count--
                        }
                        Text(
                            text = "$count",
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .align(CenterVertically)
                        )
                        ProductCountButton(icon = Icons.Default.Add) {
                            count++
                        }
                    }

                    Text(
                        text = stringResource(id = R.string._599),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = DarkOrange
                        ),
                        modifier = Modifier
                            .align(CenterVertically)
                    )

                }


            }
        }
        SpacerHeight(20.dp)
        Divider(modifier = Modifier.fillMaxWidth(), color = LineColor)

    }
}

@Composable
fun CheckOutRow(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = LineColor,
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Total",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    )
                )
                Text(
                    text = "$600",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkOrange
                    )
                )
            }
            SpacerWidth()
            Button(
                onClick = onClick,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .weight(0.7f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextColor_1,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.proceed_to_checkout),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun CheckOutScreenPreview() {
    CheckOutScreen()
}