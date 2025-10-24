package com.example.catapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoriteManager {
    private static final String PREFS_NAME = "CatAppPrefs";
    private static final String KEY_FAVORITES = "favoritos";

    private SharedPreferences prefs;

    public FavoriteManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public Set<String> getFavorites() {
        Set<String> stored = prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
        return new HashSet<>(stored);
    }

    public boolean isFavorite(String url) {
        return getFavorites().contains(url);
    }

    public void addFavorite(String url) {
        Set<String> favs = getFavorites();
        if (favs.add(url)) {
            saveFavorites(favs);
        }
    }

    public void removeFavorite(String url) {
        Set<String> favs = getFavorites();
        if (favs.remove(url)) {
            saveFavorites(favs);
        }
    }

    private void saveFavorites(Set<String> favs) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(KEY_FAVORITES, favs);
        editor.apply();
    }
}
