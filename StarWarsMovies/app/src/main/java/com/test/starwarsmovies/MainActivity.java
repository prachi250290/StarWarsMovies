package com.test.starwarsmovies;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private FilmsAdapter filmAdapter;
    private List<Film> filmList = new ArrayList<>();


    private boolean isLoading = false;
    private boolean isLastPage = false;

    private int pageNumber = 1;
    private static final int PAGE_SIZE = 30;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.movie_list_view);

        filmAdapter = new FilmsAdapter(filmList, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(filmAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        Utility.showProgressDialog(this, getString(R.string.progress_dialog_msg));
        fetchFilms(pageNumber);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    private RecyclerView.OnScrollListener
            recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView,
                                         int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    fetchFilms(++pageNumber);
                }
            }
        }
    };


    /*
    * Fetches films from the Server
    * */
    private void fetchFilms(int pageNumber) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        isLoading = true;

        Call<FilmList> call = apiService.getAllFilms(pageNumber);
        call.enqueue(new Callback<FilmList>() {
            @Override
            public void onResponse(Call<FilmList> call, Response<FilmList> response) {
                Utility.hideProgressDialog();
                isLoading = false;
                if (response != null) {
                    if (response.isSuccessful()) {
                        FilmList filmListObj = response.body();

                        List<Film> films = filmListObj.getFilms();

                        if (films != null) {

                            //Update the film list
                            filmList.addAll(films);
                            filmAdapter.setFilmList(films);
                            filmAdapter.notifyDataSetChanged();
                            if (films.size() < PAGE_SIZE) {
                                isLastPage = true;
                            }
                        }
                    } else {
                        //Handle failure
                    }
                }
            }

            @Override
            public void onFailure(Call<FilmList> call, Throwable t) {
                // Log error here since request failed
                Utility.hideProgressDialog();
                Log.e(TAG, t.toString());
            }
        });
    }


}
