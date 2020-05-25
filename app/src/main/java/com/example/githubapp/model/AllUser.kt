package com.example.githubapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllUser(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int,
    @SerializedName("incomplete_results")
    @Expose
    val incompleteResults: Boolean,
    @SerializedName("items")
    @Expose
    val items: List<Item>
) : Serializable


data class Item(
    @SerializedName("login")
    @Expose
    val login: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("node_id")
    @Expose
    val nodeID: String,
    @SerializedName("avatar_url")
    @Expose
    val avatarURL: String,
    @SerializedName("gravatar_id")
    @Expose
    val gravatarID: String,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("html_url")
    @Expose
    val htmlURL: String,
    @SerializedName("followers_url")
    @Expose
    val followersURL: String,
    @SerializedName("subscriptions_url")
    @Expose
    val subscriptionsURL: String,
    @SerializedName("organizations_url")
    @Expose
    val organizationsURL: String,
    @SerializedName("repos_url")
    @Expose
    val reposURL: String,
    @SerializedName("received_events_url")
    @Expose
    val receivedEventsURL: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("score")
    @Expose
    val score: Int
) : Serializable