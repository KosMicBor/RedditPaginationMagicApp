package kosmicbor.redditpaginationmagicapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kosmicbor.redditpaginationmagicapp.domain.MainScreenUseCase
import kosmicbor.redditpaginationmagicapp.domain.RedditPost
import kotlinx.coroutines.flow.*

class MainScreenViewModel(private val usecase: MainScreenUseCase) : ViewModel() {

    lateinit var query: StateFlow<PagingData<RedditPost>>

    fun getPostsList() {
        query = usecase.getPostList().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

}