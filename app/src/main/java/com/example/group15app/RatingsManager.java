package com.example.group15app;

import android.content.Context;
import android.content.SharedPreferences;

public class RatingsManager {
    private static final String PREFS = "dealmate_ratings";

    public static void saveRating(Context ctx, String dealTitle, float rating) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
           .putFloat(dealTitle, rating).apply();
    }

    public static float getRating(Context ctx, String dealTitle) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                  .getFloat(dealTitle, 0f);
    }
}
