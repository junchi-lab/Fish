package com.junechee.fish.domain.model

data class Board constructor(
    val userId:Long,
    val id:Long,
    val title:String,
    val content:String,
    val images:List<String>,
    val username:String,
    val profileImageUrl:String,
    val comments:List<Comment>
)