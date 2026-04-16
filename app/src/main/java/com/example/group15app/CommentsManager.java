package com.example.group15app;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommentsManager {
    private static final String PREFS = "dealmate_comments";

    public static void addComment(Context ctx, String dealTitle, String comment) {
        List<String> list = getComments(ctx, dealTitle);
        list.add(comment);
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
           .putString(dealTitle, new Gson().toJson(list)).apply();
    }

    public static List<String> getComments(Context ctx, String dealTitle) {
        String json = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                         .getString(dealTitle, null);
        if (json == null) return new ArrayList<>();
        Type t = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(json, t);
    }
}
