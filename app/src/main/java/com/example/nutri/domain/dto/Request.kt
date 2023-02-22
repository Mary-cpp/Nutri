package com.example.nutri.domain.dto

data class Request (
    val appId: String,
    val appKey: String,
    val q: String?
    ){
}