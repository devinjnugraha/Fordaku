package com.fordaku

data class Post(
    // TODO: Validate image data type, create real relation to fordaId
    val title: String,
    val content: String,
    val image: String,
    val created: String,
    val fordaId: String, // foreign key Forda
)
