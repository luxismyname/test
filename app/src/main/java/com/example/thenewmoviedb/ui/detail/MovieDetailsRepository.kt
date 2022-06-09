package com.example.thenewmoviedb.ui.detail

import androidx.lifecycle.LiveData
import com.example.thenewmoviedb.data.repository.MovieDetailsNetworkDataSource
import com.example.thenewmoviedb.data.api.TheMovieDbInterface
import com.example.thenewmoviedb.data.repository.NetworkState
import com.example.thenewmoviedb.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: TheMovieDbInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails>{

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)
        return movieDetailsNetworkDataSource.downloadedMovieDetailsResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }

}