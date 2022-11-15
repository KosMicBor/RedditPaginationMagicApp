package kosmicbor.redditpaginationmagicapp.domain

import kosmicbor.redditpaginationmagicapp.data.retrofit.dto.RedditHotDataChildrenDTO
import kosmicbor.redditpaginationmagicapp.data.room.RedditPostLocalDto

interface Mapper {
    fun convertListDtoToLocalList(postListDTO: List<RedditHotDataChildrenDTO>): List<RedditPostLocalDto>
}