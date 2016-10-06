package com.test.starwarsmovies;

import android.app.ProgressDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prachi on 04/10/16.
 */
public class Utility {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String Message) {

        showProgressDialog(context, Message, false);
    }

    public static void showProgressDialog(Context context, String Message,
                                          boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage(Message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }



    public static String getReleaseDate(String releaseDate) {
        String desiredDate = "";
        SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat desiredFormat = new SimpleDateFormat("MMMM dd yyyy");
        try {
            Date date = currentFormat.parse(releaseDate);
            desiredDate = desiredFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return desiredDate;
    }

}
