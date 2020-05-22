package com.example.githubapp.model

data class AllUser (
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<Item>
)

data class Item (
    val login: String,
    val id: Long,
    val nodeID: String,
    val avatarURL: String,
    val gravatarID: String,
    val url: String,
    val htmlURL: String,
    val followersURL: String,
    val subscriptionsURL: String,
    val organizationsURL: String,
    val reposURL: String,
    val receivedEventsURL: String,
    val type: String,
    val score: Long
)