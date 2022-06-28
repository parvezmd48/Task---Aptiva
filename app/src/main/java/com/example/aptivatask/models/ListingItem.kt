package com.example.aptivatask.models

import android.os.Parcelable
import java.io.Serializable

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingItem(
    @SerializedName("created_at")
    val createdAt: String,

    val price: String,
    val name: String,
    val uid: String,

    @SerializedName("image_ids")
    val imageIDS: List<String>,

    @SerializedName("image_urls")
    val imageUrls: List<String>,

    @SerializedName("image_urls_thumbnails")
    val imageUrlsThumbnails: List<String>
) : Parcelable
