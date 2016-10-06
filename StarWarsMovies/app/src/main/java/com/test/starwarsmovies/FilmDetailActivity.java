package com.test.starwarsmovies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FilmDetailActivity extends Activity {

    private TextView titleTextView, directorTextView, releaseDateTextView, openingCrawlTextView, producerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        initializeViews();

        setFonts();

        setValues();
    }

    private void initializeViews() {
        titleTextView = (TextView) findViewById(R.id.film_title_textview);
        directorTextView = (TextView) findViewById(R.id.film_director_textview);
        releaseDateTextView = (TextView) findViewById(R.id.film_release_date_textview);
        openingCrawlTextView = (TextView) findViewById(R.id.film_open_crawl_textview);
        producerTextView = (TextView) findViewById(R.id.film_producer_textview);
    }

    private void setFonts() {
        //Apply Fonts
        Fonts.getSharedFontsManager().setFont(this, titleTextView, Fonts.BANDY_REGULAR);
        Fonts.getSharedFontsManager().setFont(this, directorTextView, Fonts.BANDY_REGULAR);
        Fonts.getSharedFontsManager().setFont(this, releaseDateTextView, Fonts.BANDY_REGULAR);
        Fonts.getSharedFontsManager().setFont(this, producerTextView, Fonts.BANDY_REGULAR);

    }

    private void setValues() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titleTextView.setText(extras.getString(Constants.INTENT_KEY_FILM_TITLE));
            directorTextView.setText("Directed by " + extras.getString(Constants.INTENT_KEY_FILM_DIRECTOR));
            releaseDateTextView.setText("Released on " + extras.getString(Constants.INTENT_KEY_FILM_RELEASE_DATE));
            openingCrawlTextView.setText(extras.getString(Constants.INTENT_KEY_OPENING_CRAWL));
            producerTextView.setText("Produced by " + extras.getString(Constants.INTENT_KEY_FILM_PRODUCER));
        }
    }

}
