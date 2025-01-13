package com.jsn.quickgrab.data.remote.model

data class Product(
    var _id: String,
    val productId: Int,
    val title: String,
    val image: String,
    val price: String
) {
    constructor():this("",0,"","","")
}
