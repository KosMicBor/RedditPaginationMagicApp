package kosmicbor.redditpaginationmagicapp.data.retrofit

import kosmicbor.redditpaginationmagicapp.data.retrofit.dto.RedditHotDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditAPI {
    @GET("r/technology/hot.json")
    suspend fun getNextList(@Query("after") afterName: String?): Response<RedditHotDTO>
}