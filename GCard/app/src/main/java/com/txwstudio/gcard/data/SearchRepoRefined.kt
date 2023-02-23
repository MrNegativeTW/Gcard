package com.txwstudio.gcard.data

import com.google.gson.annotations.SerializedName

/**
 * 簡化版 [SearchRepoApiModel]，不需要這麼多資訊
 */
data class SearchRepoRefined(
    @SerializedName("id") val id: Long,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner
)
