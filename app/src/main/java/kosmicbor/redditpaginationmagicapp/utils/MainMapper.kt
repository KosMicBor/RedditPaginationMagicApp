package kosmicbor.redditpaginationmagicapp.utils

import kosmicbor.redditpaginationmagicapp.data.retrofit.dto.RedditHotDataChildrenDTO
import kosmicbor.redditpaginationmagicapp.data.room.RedditPostLocalDto
import kosmicbor.redditpaginationmagicapp.domain.Mapper

class MainMapper : Mapper {
    override fun convertListDtoToLocalList(postListDTO: List<RedditHotDataChildrenDTO>): List<RedditPostLocalDto> {
        return postListDTO.map {
            RedditPostLocalDto(
                id = 0,
                fullAuthorName = it.data.fullAuthorName ?: "",
                title = it.data.title,
                ups = it.data.ups,
                numberOfComments = it.data.numberOfComments
            )
        }
    }
}