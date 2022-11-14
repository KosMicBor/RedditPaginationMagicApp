package kosmicbor.redditpaginationmagicapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getPosts(): Flow<PagingData<RedditPost>>
}