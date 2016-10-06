package com.test.starwarsmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prachi on 04/10/16.
 */
public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {

    private List<Film> filmList;
    private Context context;

    public FilmsAdapter(List<Film> filmList, Context context) {
        this.filmList = filmList;
        this.context = context;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_list_row, parent, false);

        return new FilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.title.setText(film.getTitle());
        holder.director.setText("Directed by : "+ film.getDirector());

        if (film.getRelease_date() != null && film.getRelease_date() != "") {
            holder.releaseDate.setText(Utility.getReleaseDate(film.getRelease_date()));

        }
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        public TextView title, releaseDate, director;

        public FilmViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.film_title_textview);
            releaseDate = (TextView) view.findViewById(R.id.release_date_textview);
            director = (TextView) view.findViewById(R.id.film_director_textview);

            Fonts.getSharedFontsManager().setFont(context, title, Fonts.BANDY_REGULAR);
            Fonts.getSharedFontsManager().setFont(context, director, Fonts.BANDY_REGULAR);
            Fonts.getSharedFontsManager().setFont(context, releaseDate, Fonts.CATHERINE);

        }
    }


}
