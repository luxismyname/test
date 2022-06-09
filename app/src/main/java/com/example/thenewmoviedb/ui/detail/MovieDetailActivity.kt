package com.example.thenewmoviedb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.thenewmoviedb.R
import com.example.thenewmoviedb.data.api.POSTER_BASE_URL
import com.example.thenewmoviedb.data.api.TheMovieDbClient
import com.example.thenewmoviedb.data.api.TheMovieDbInterface
import com.example.thenewmoviedb.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.*
import java.text.NumberFormat
import java.util.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.hide()

        val movieId: Int = intent.getIntExtra("id",1)
        val apiService: TheMovieDbInterface = TheMovieDbClient.getClient()
        movieDetailsRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUi(it)
        })


    }

    fun bindUi(it: MovieDetails){

        tvMovieTitleDetail.text = it.title
        tvTagLine.text = it.tagline
        tvRelease.text = it.releaseDate
        tvRating.text = it.rating.toString()
        tvRuntime.text = it.runtime.toString() + " minus"
        tvOverview.text = it.overview

        val formatCurrency : NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        tvBudget.text = formatCurrency.format(it.budget)
        tvRevenue.text = formatCurrency.format(it.revenue)
        val moviePosterURL: String = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(imgPosterDetail)

    }


    private fun getViewModel(movieId: Int): SingleMovieViewModel{
        return ViewModelProvider(this,object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieDetailsRepository, movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }

}