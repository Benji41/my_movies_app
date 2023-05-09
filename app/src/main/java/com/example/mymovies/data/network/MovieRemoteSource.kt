package com.example.mymovies.data.network

import com.example.mymovies.data.network.RETROFIT.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRemoteSource {

    sealed class Requests{
        abstract val name : String
        class GetPopularMovies (override val name: String = "Popular Movies") : Requests()
        class GetActualMovies (override val name: String = "Actual Movies") : Requests()

        suspend fun getMovies() : List<MovieRemote> {
            return when(this){
                is GetActualMovies -> getMoviesByAPI(API::getAllPlayingNowMovies)
                is GetPopularMovies -> getMoviesByAPI(API::getAllTopRatedMovies)
            }
        }

        private suspend fun getMoviesByAPI(
            APICall : suspend () -> Response<ResponseMoviesAPI>
        ): List<MovieRemote> {
            return withContext(Dispatchers.IO){
                val response = APICall.invoke()
                if (response.code() == 200){
                    response.body()?.results ?: listOf()
                }else{
                    throw Exception("Error Coming from ${this@Requests.name} request: ${response.errorBody()}")
                }
            }

        }
    }


}
