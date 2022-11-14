package kosmicbor.redditpaginationmagicapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kosmicbor.redditpaginationmagicapp.data.MainPagingSourceImpl.Companion.DEFAULT_PAGE_SIZE
import kosmicbor.redditpaginationmagicapp.domain.MainPagingSource
import kosmicbor.redditpaginationmagicapp.domain.MainRepository
import kosmicbor.redditpaginationmagicapp.domain.RedditPost
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val pagingSource: MainPagingSource
) : MainRepository {

    @Suppress("UNCHECKED_CAST")
    override fun getPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                initialLoadSize = DEFAULT_PAGE_SIZE
            ),
            pagingSourceFactory = { pagingSource as PagingSource<String, RedditPost> }
        ).flow
    }
}