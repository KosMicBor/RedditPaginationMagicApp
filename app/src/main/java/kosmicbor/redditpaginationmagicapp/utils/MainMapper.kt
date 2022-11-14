package kosmicbor.redditpaginationmagicapp.utils

import kosmicbor.redditpaginationmagicapp.data.retrofit.dto.RedditHotDataChildrenDTO
import kosmicbor.redditpaginationmagicapp.domain.Mapper
import kosmicbor.redditpaginationmagicapp.domain.RedditPost

class MainMapper : Mapper {
    override fun convertListDtoToPostList(postListDTO: List<RedditHotDataChildrenDTO>): List<RedditPost> {
        return postListDTO.map {
            RedditPost(
                fullAuthorName = it.data.fullAuthorName,
                title = it.data.title,
                ups = it.data.ups,
                numberOfComments = it.data.numberOfComments
            )
        }
    }
}