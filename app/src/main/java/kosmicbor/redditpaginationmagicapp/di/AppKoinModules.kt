package kosmicbor.redditpaginationmagicapp.di

import kosmicbor.redditpaginationmagicapp.data.MainPagingSourceImpl
import kosmicbor.redditpaginationmagicapp.data.MainRepositoryImpl
import kosmicbor.redditpaginationmagicapp.data.MainScreenUseCaseImpl
import kosmicbor.redditpaginationmagicapp.data.retrofit.RedditAPI
import kosmicbor.redditpaginationmagicapp.domain.MainPagingSource
import kosmicbor.redditpaginationmagicapp.domain.MainRepository
import kosmicbor.redditpaginationmagicapp.domain.MainScreenUseCase
import kosmicbor.redditpaginationmagicapp.ui.MainScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val REDDIT_API_NAME = "REDDIT_API_NAME"
const val REMOTE_CLIENT_NAME = "REMOTE_CLIENT_NAME"
const val MAIN_PAGING_SOURCE_NAME = "MAIN_PAGING_SOURCE_NAME"
const val MAIN_REPOSITORY_NAME = "MAIN_REPOSITORY"
const val MAIN_SCREEN_USECASE_NAME = "MAIN_SCREEN_USECASE_NAME"
const val MAIN_SCREEN_VIEWMODEL_NAME = "MAIN_SCREEN_VIEWMODEL_NAME"
const val BASE_URL = "https://www.reddit.com/"

val mainAppModule = module {

    single<Retrofit>(qualifier = named(REDDIT_API_NAME)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get(qualifier = named(REMOTE_CLIENT_NAME)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(qualifier = named(REMOTE_CLIENT_NAME)) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<MainPagingSource>(qualifier = named(MAIN_PAGING_SOURCE_NAME)) {
        MainPagingSourceImpl(
            get<Retrofit>(qualifier = named(REDDIT_API_NAME)).create(
                RedditAPI::class.java
            )
        )
    }

    single<MainRepository>(qualifier = named(MAIN_REPOSITORY_NAME)) {
        MainRepositoryImpl(get(qualifier = named(MAIN_PAGING_SOURCE_NAME)))
    }

    factory<MainScreenUseCase>(qualifier = named(MAIN_SCREEN_USECASE_NAME)) {
        MainScreenUseCaseImpl(get(qualifier = named(MAIN_REPOSITORY_NAME)))
    }

    viewModel(qualifier = named(MAIN_SCREEN_VIEWMODEL_NAME)) {
        MainScreenViewModel(get(qualifier = named(MAIN_SCREEN_USECASE_NAME)))
    }
}