package com.example.thenewmoviedb.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thenewmoviedb.data.api.TheMovieDbInterface
import com.example.thenewmoviedb.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsNetworkDataSource (private val apiService: TheMovieDbInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState

    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieDetailsResponse: LiveData<MovieDetails>
    get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int){

        _networkState.postValue(NetworkState.LOADING)
        try {

            compositeDisposable.add(

                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({

                        _downloadedMovieDetailsResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)

                    }, {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDetailsDataSouce", it.message.toString())
                    })

            )

        } catch (e: Exception){
            Log.e("MovieDetailsDataSouce", e.message.toString())
        }

    }


}