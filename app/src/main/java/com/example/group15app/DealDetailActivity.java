package com.example.group15app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        String title       = getIntent().getStringExtra("title");
        String store       = getIntent().getStringExtra("store");
        String discount    = getIntent().getStringExtra("discount");
        String description = getIntent().getStringExtra("description");
        String category    = getIntent().getStringExtra("category");
        String url         = getIntent().getStringExtra("url");

        ((TextView) findViewById(R.id.tvDetailTitle)).setText(title);
        ((TextView) findViewById(R.id.tvDetailStore)).setText(store);
        ((TextView) findViewById(R.id.tvDetailDiscount)).setText(discount);
        ((TextView) findViewById(R.id.tvDetailDescription)).setText(description);
        ((TextView) findViewById(R.id.tvDetailCategory)).setText("📁 " + category);

        Button btnSave = findViewById(R.id.btnSaveFavourite);

        // Show correct label if already saved
        if (FavouritesManager.isFavourite(this, title)) {
            btnSave.setText("❤  Already in Favourites");
            btnSave.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#888888")));
        }

        btnSave.setOnClickListener(v -> {
            if (FavouritesManager.isFavourite(this, title)) {
                Toast.makeText(this, "Already saved!", Toast.LENGTH_SHORT).show();
            } else {
                // Persist to SharedPreferences
                FavouritesManager.addFavourite(this, title);
                btnSave.setText("❤  Already in Favourites");
                btnSave.setBackgroundTintList(
                    android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#888888")));
                Toast.makeText(this, "Saved to Favourites!", Toast.LENGTH_SHORT).show();
            }
        });

        // Open in browser
        Button btnVisit = findViewById(R.id.btnVisitWebsite);
        btnVisit.setOnClickListener(v -> {
            if (url != null && !url.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } else {
                Toast.makeText(this, "No website available", Toast.LENGTH_SHORT).show();
            }
        });

        // Share deal
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> {
            String shareText = "🎓 DealMate Student Deal!\n\n"
                    + title + " at " + store + "\n"
                    + discount + "\n\n"
                    + description + "\n\n"
                    + "Check it out: " + url;
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share this deal via..."));
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
