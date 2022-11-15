package kosmicbor.redditpaginationmagicapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reddit_posts")
data class RedditPostLocalDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullAuthorName: String,
    val title: String,
    val ups: Int,
    val numberOfComments: Int
)