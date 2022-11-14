package kosmicbor.redditpaginationmagicapp.data

import androidx.paging.PagingData
import kosmicbor.redditpaginationmagicapp.domain.MainRepository
import kosmicbor.redditpaginationmagicapp.domain.MainScreenUseCase
import kosmicbor.redditpaginationmagicapp.domain.RedditPost
import kotlinx.coroutines.flow.Flow

class MainScreenUseCaseImpl(private val repository: MainRepository) : MainScreenUseCase {
    override fun getPostList(): Flow<PagingData<RedditPost>> {
        return repository.getPosts()
    }
}