package com.example.group15app;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouritesManager {
    private static final String PREFS = "dealmate_prefs";
    private static final String KEY   = "favourites";

    public static void add(Context ctx, String title) {
        List<String> list = get(ctx);
        if (!list.contains(title)) { list.add(title); save(ctx, list); }
    }

    public static void remove(Context ctx, String title) {
        List<String> list = get(ctx); list.remove(title); save(ctx, list);
    }

    public static List<String> get(Context ctx) {
        String json = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, null);
        if (json == null) return new ArrayList<>();
        Type t = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(json, t);
    }

    public static boolean isFav(Context ctx, String title) { return get(ctx).contains(title); }

    private static void save(Context ctx, List<String> list) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
           .putString(KEY, new Gson().toJson(list)).apply();
    }
}
