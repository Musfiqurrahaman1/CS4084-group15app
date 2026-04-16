package com.example.group15app;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class FavouritesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        findViewById(R.id.btnBackFavourites).setOnClickListener(v -> finish());
        loadFavourites();
    }

    @Override protected void onResume() { super.onResume(); loadFavourites(); }

    private void loadFavourites() {
        ListView lv    = findViewById(R.id.listFavourites);
        TextView empty = findViewById(R.id.tvEmptyFavourites);
        List<String> favs = FavouritesManager.get(this);
        if (favs.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, favs);
            lv.setAdapter(adapter);
            lv.setOnItemLongClickListener((p, v, pos, id) -> {
                FavouritesManager.remove(this, favs.get(pos));
                Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
                loadFavourites();
                return true;
            });
        }
    }
}
