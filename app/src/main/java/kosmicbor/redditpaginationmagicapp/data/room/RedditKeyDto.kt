package kosmicbor.redditpaginationmagicapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reddit_keys")
data class RedditKeyDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prevKey: String?,
    val nextKey: String?
)