package kosmicbor.redditpaginationmagicapp.data

import androidx.paging.*
import kosmicbor.redditpaginationmagicapp.data.retrofit.RedditAPI
import kosmicbor.redditpaginationmagicapp.data.room.MainRedditKeysDao
import kosmicbor.redditpaginationmagicapp.data.room.MainRedditPostDao
import kosmicbor.redditpaginationmagicapp.data.room.RedditKeyDto
import kosmicbor.redditpaginationmagicapp.data.room.RedditPostLocalDto
import kosmicbor.redditpaginationmagicapp.domain.Mapper
import kosmicbor.redditpaginationmagicapp.utils.MainMapper
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MainPagingMediator(
    private val redditAPI: RedditAPI,
    private val localKeysDao: MainRedditKeysDao,
    private val localPostsDao: MainRedditPostDao
) : RemoteMediator<String, RedditPostLocalDto>() {

    private val mapper: Mapper = MainMapper()
    private var currentAnchorItem: String? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<String, RedditPostLocalDto>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    lastItem.fullAuthorName
                }
            }

            val response = redditAPI.getNextList(afterName = loadKey)

            if (response.isSuccessful) {

                if (loadKey == null) {
                    val currentLocalKeyDto =
                        RedditKeyDto(
                            id = 0,
                            prevKey = null,
                            nextKey = response.body()!!.data.after
                        )

                    localKeysDao.insertOrReplace(currentLocalKeyDto)
                } else {

                    val nextAnchorItem = response.body()!!.data.after
                    val prevAnchorItem = currentAnchorItem
                    currentAnchorItem = nextAnchorItem
                    val currentLocalKeyDto =
                        RedditKeyDto(
                            id = 0,
                            prevKey = prevAnchorItem,
                            nextKey = nextAnchorItem
                        )

                    localKeysDao.insertOrReplace(currentLocalKeyDto)

                    val newRedditPostsList =
                        mapper.convertListDtoToLocalList(response.body()!!.data.childrenList)

                    localPostsDao.insertAll(newRedditPostsList)
                }
            } else {
                MediatorResult.Error(HttpException(response))
            }
            MediatorResult.Success(
                endOfPaginationReached = response.body()!!.data.after == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)

        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}