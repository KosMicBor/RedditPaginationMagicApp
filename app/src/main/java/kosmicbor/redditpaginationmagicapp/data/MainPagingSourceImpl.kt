package kosmicbor.redditpaginationmagicapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kosmicbor.redditpaginationmagicapp.data.retrofit.RedditAPI
import kosmicbor.redditpaginationmagicapp.domain.MainPagingSource
import kosmicbor.redditpaginationmagicapp.domain.Mapper
import kosmicbor.redditpaginationmagicapp.domain.RedditPost
import kosmicbor.redditpaginationmagicapp.utils.MainMapper
import retrofit2.HttpException

class MainPagingSourceImpl(
    private val redditAPI: RedditAPI,
) : PagingSource<String, RedditPost>(), MainPagingSource {

    private val mapper: Mapper = MainMapper()
    private var currentAnchorItem: String? = null

    companion object {
        const val DEFAULT_PAGE_SIZE = 25
    }

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)
        return anchorPage?.prevKey ?: anchorPage?.nextKey
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        try {
            val pageAnchor = params.key

            val response = redditAPI.getNextList(pageAnchor)

            return if (response.isSuccessful) {
                val listOfPosts =
                    mapper.convertListDtoToPostList(response.body()!!.data.childrenList)
                val nextAnchorItem = response.body()!!.data.after
                val prevAnchorItem = currentAnchorItem
                currentAnchorItem = nextAnchorItem

                LoadResult.Page(listOfPosts, prevAnchorItem, nextAnchorItem)
            } else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}