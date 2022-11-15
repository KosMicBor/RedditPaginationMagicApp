package kosmicbor.redditpaginationmagicapp.data

import androidx.paging.*
import kosmicbor.redditpaginationmagicapp.data.MainPagingSourceImpl.Companion.DEFAULT_PAGE_SIZE
import kosmicbor.redditpaginationmagicapp.data.room.RedditPostLocalDto
import kosmicbor.redditpaginationmagicapp.domain.MainRepository
import kosmicbor.redditpaginationmagicapp.domain.RedditPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl(
    private val pagingSource: PagingSource<String, RedditPostLocalDto>,
    private val pagingMediator: MainPagingMediator
) : MainRepository {

    @OptIn(ExperimentalPagingApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun getPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                initialLoadSize = DEFAULT_PAGE_SIZE * 2
            ),
            remoteMediator = pagingMediator,
            pagingSourceFactory = { pagingSource }
        ).flow.map { pagingData ->
            pagingData.map {
                RedditPost(
                    fullAuthorName = it.fullAuthorName,
                    title = it.title,
                    ups = it.ups,
                    numberOfComments = it.numberOfComments
                )
            }
        }
    }
}