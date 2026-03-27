package com.example.group15app;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouritesManager {

    private static final String PREFS_NAME = "dealmate_prefs";
    private static final String KEY_FAVS   = "favourites";

    public static void addFavourite(Context context, String dealTitle) {
        List<String> favs = getFavourites(context);
        if (!favs.contains(dealTitle)) {
            favs.add(dealTitle);
            save(context, favs);
        }
    }

    public static void removeFavourite(Context context, String dealTitle) {
        List<String> favs = getFavourites(context);
        favs.remove(dealTitle);
        save(context, favs);
    }

    public static List<String> getFavourites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_FAVS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    public static boolean isFavourite(Context context, String dealTitle) {
        return getFavourites(context).contains(dealTitle);
    }

    private static void save(Context context, List<String> favs) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_FAVS, new Gson().toJson(favs)).apply();
    }
}
