package kosmicbor.redditpaginationmagicapp.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GetPostListUseCase {
    fun getPostList(): Flow<PagingData<RedditPost>>
}