package kosmicbor.redditpaginationmagicapp.domain

data class RedditPost(
    val fullAuthorName: String?,
    val title: String,
    val ups: Int,
    val numberOfComments: Int,
)