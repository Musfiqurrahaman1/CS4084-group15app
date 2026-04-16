package com.example.group15app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class DealDetailActivity extends AppCompatActivity {

    String title, store, discount, description, category, url, address, eircode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);

        title       = getIntent().getStringExtra("title");
        store       = getIntent().getStringExtra("store");
        discount    = getIntent().getStringExtra("discount");
        description = getIntent().getStringExtra("description");
        category    = getIntent().getStringExtra("category");
        url         = getIntent().getStringExtra("url");
        address     = getIntent().getStringExtra("address");
        eircode     = getIntent().getStringExtra("eircode");

        ((TextView) findViewById(R.id.tvDetailTitle)).setText(title);
        ((TextView) findViewById(R.id.tvDetailStore)).setText(store);
        ((TextView) findViewById(R.id.tvDetailDiscount)).setText(discount);
        ((TextView) findViewById(R.id.tvDetailDescription)).setText(description);
        ((TextView) findViewById(R.id.tvDetailCategory)).setText(category);
        ((TextView) findViewById(R.id.tvDetailAddress)).setText(address + "  |  Eircode: " + eircode);

        // Existing rating
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float saved = RatingsManager.getRating(this, title);
        ratingBar.setRating(saved);
        ratingBar.setOnRatingBarChangeListener((rb, rating, fromUser) -> {
            if (fromUser) {
                RatingsManager.saveRating(this, title, rating);
                Toast.makeText(this, "Rating saved!", Toast.LENGTH_SHORT).show();
            }
        });

        // Save favourite
        Button btnFav = findViewById(R.id.btnSaveFavourite);
        updateFavBtn(btnFav);
        btnFav.setOnClickListener(v -> {
            if (FavouritesManager.isFav(this, title)) {
                FavouritesManager.remove(this, title);
            } else {
                FavouritesManager.add(this, title);
            }
            updateFavBtn(btnFav);
        });

        // Visit website
        findViewById(R.id.btnVisitWebsite).setOnClickListener(v ->
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))));

        // Open in Google Maps
        findViewById(R.id.btnOpenMap).setOnClickListener(v -> {
            String mapUri = "https://www.google.com/maps/search/?api=1&query=" +
                    Uri.encode(store + " " + address + " Ireland");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapUri)));
        });

        // Share
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            String text = "DealMate Student Deal!\n\n" + title + " at " + store +
                    "\n" + discount + "\n\n" + description + "\n\nAddress: " + address +
                    "\n\nCheck it out: " + url;
            Intent si = new Intent(Intent.ACTION_SEND);
            si.setType("text/plain");
            si.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(si, "Share deal via..."));
        });

        // Comments
        loadComments();
        Button btnComment = findViewById(R.id.btnAddComment);
        EditText etComment = findViewById(R.id.etComment);
        btnComment.setOnClickListener(v -> {
            String c = etComment.getText().toString().trim();
            if (!c.isEmpty()) {
                CommentsManager.addComment(this, title, c);
                etComment.setText("");
                loadComments();
                Toast.makeText(this, "Comment added!", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void updateFavBtn(Button btn) {
        if (FavouritesManager.isFav(this, title)) {
            btn.setText("Remove from Favourites");
            btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFE53935));
        } else {
            btn.setText("Save to Favourites");
            btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFFF6B35));
        }
    }

    private void loadComments() {
        List<String> comments = CommentsManager.getComments(this, title);
        LinearLayout ll = findViewById(R.id.llComments);
        ll.removeAllViews();
        for (String c : comments) {
            TextView tv = new TextView(this);
            tv.setText("- " + c);
            tv.setTextSize(14);
            tv.setPadding(0, 8, 0, 8);
            tv.setTextColor(0xFF333333);
            ll.addView(tv);
        }
    }
}
