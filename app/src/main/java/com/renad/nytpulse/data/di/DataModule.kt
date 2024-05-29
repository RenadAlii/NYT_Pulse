package com.renad.nytpulse.data.di

import com.renad.nytpulse.data.MostViewedArticlesRepositoryImp
import com.renad.nytpulse.data.source.MostViewedArticlesRemoteDataSource
import com.renad.nytpulse.data.source.remote.MostViewedArticlesRemoteDataSourceImp
import com.renad.nytpulse.domain.repos.MostViewedArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {
    @Provides
    fun provideMostViewedArticlesRemoteDataSource(datasource: MostViewedArticlesRemoteDataSourceImp): MostViewedArticlesRemoteDataSource =
        datasource

    @Provides
    fun provideMostViewedArticlesRepository(repo: MostViewedArticlesRepositoryImp): MostViewedArticlesRepository =
        repo
}
