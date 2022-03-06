package com.example.newsapp.ui

data class NewsRespons(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)