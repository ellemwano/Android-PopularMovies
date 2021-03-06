package com.mwano.lauren.popular_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mwano.lauren.popular_movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ElleMwa on 25/02/2018.
 */


/**
 * Create the adapter extending from RecyclerView.Adapter
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mMovies;
    private final MovieAdapterOnClickHandler mClickHandler;

    /**
     * Create an OnClickHandler interface
     */
    public interface MovieAdapterOnClickHandler {
        void onClick(Movie currentMovie);
    }

    public MovieAdapter (Context context, ArrayList<Movie> movies, MovieAdapterOnClickHandler clickHandler){
        mContext = context;
        mMovies = movies;
        mClickHandler = clickHandler;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForMovieItem = R.layout.movie_item;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View mItemView = mInflater.inflate(layoutIdForMovieItem, parent, false);
        return new MovieViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int position) {
        mContext = movieViewHolder.mMovieImageView.getContext();
        Movie mCurrentMovie = mMovies.get(position);
        Picasso.with(mContext).load(Movie.buildFullPosterPath(mCurrentMovie))
                .into(movieViewHolder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) return 0;
        return mMovies.size();
    }

    public void setMovieData(ArrayList<Movie> movieData) {
        mMovies = movieData;
        notifyDataSetChanged();
    }

    /**
     * Create a ViewHolder with a click listener
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMovieImageView;

        public MovieViewHolder(View view) {
            super(view);
            mMovieImageView = (ImageView)view.findViewById(R.id.movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           int adapterPosition = getAdapterPosition();
           Movie currentMovie = mMovies.get(adapterPosition);
           mClickHandler.onClick(currentMovie);
        }
    }

}
