package kosmicbor.redditpaginationmagicapp.data.retrofit.dto

import com.google.gson.annotations.SerializedName

data class RedditHotDTO(
    @SerializedName("data")
    val data: RedditHotDataDTO
)

data class RedditHotDataDTO(
    @SerializedName("after")
    val after: String?,
    @SerializedName("children")
    val childrenList: List<RedditHotDataChildrenDTO>
)

data class RedditHotDataChildrenDTO(
    @SerializedName("data")
    val data: RedditPostDTO
)

data class RedditPostDTO(
    @SerializedName("author_fullname")
    val fullAuthorName: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("num_comments")
    val numberOfComments: Int
)