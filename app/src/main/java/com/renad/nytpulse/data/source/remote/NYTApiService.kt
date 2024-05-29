package com.renad.nytpulse.data.source.remote

import com.renad.nytpulse.data.model.MostViewedArticlesModel
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTApiService {
    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getMostViewedArticle(
        @Path("period") period: String,
    ): MostViewedArticlesModel
}
