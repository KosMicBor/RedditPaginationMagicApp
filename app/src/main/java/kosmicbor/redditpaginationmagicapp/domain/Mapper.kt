package kosmicbor.redditpaginationmagicapp.domain

import kosmicbor.redditpaginationmagicapp.data.retrofit.dto.RedditHotDataChildrenDTO

interface Mapper {
    fun convertListDtoToPostList(postListDTO: List<RedditHotDataChildrenDTO>): List<RedditPost>
}