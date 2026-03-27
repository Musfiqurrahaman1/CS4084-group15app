package com.example.group15app;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    ListView listView;
    TextView tvEmpty;
    ArrayAdapter<String> adapter;
    List<String> favs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listView = findViewById(R.id.listFavourites);
        tvEmpty  = findViewById(R.id.tvEmptyFavourites);

        loadFavourites();

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            String dealTitle = favs.get(position);
            FavouritesManager.removeFavourite(this, dealTitle);
            Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show();
            loadFavourites(); // refresh
            return true;
        });

        findViewById(R.id.btnBackFavourites).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavourites(); // refresh when coming back to this screen
    }

    private void loadFavourites() {
        favs = FavouritesManager.getFavourites(this);
        if (favs.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favs);
            listView.setAdapter(adapter);
        }
    }
}
