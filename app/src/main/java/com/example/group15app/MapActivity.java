package com.example.group15app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.*;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        RecyclerView rv = findViewById(R.id.rvStores);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Deal> deals = DataManager.getDeals();
        StoreAdapter adapter = new StoreAdapter(deals, deal -> {
            String uri = "https://www.google.com/maps/search/?api=1&query=" +
                    Uri.encode(deal.getStore() + " " + deal.getAddress() + " Ireland");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        });
        rv.setAdapter(adapter);
        findViewById(R.id.btnBackMap).setOnClickListener(v -> finish());
    }
}
