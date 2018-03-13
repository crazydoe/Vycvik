package com.example.michal.vycvik;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by michal on 13.06.2017.
 */

public class Utils {
    public static abstract class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        public Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        public abstract void onPostExecute(Bitmap result);
    }
}
