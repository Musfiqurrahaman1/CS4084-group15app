package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements DealAdapter.OnDealClickListener {

    RecyclerView recyclerView;
    DealAdapter adapter;
    List<Deal> filteredDeals;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.rvCategoryDeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Category buttons
        int[] btnIds = {R.id.btnCatFood, R.id.btnCatCoffee, R.id.btnCatFitness,
                        R.id.btnCatTech, R.id.btnCatTransport,
                        R.id.btnCatEntertainment, R.id.btnCatBeauty};
        String[] cats = {"Food", "Coffee", "Fitness", "Tech", "Transport", "Entertainment", "Beauty"};

        for (int i = 0; i < btnIds.length; i++) {
            final String cat = cats[i];
            findViewById(btnIds[i]).setOnClickListener(v -> loadCategory(cat));
        }

        filteredDeals = new ArrayList<>();
        adapter = new DealAdapter(filteredDeals, this);
        recyclerView.setAdapter(adapter);

        // Load Food by default
        loadCategory("Food");

        findViewById(R.id.btnBackCategories).setOnClickListener(v -> finish());
    }

    private void loadCategory(String cat) {
        category = cat;
        filteredDeals.clear();
        for (Deal d : MainActivity.getHardcodedDeals()) {
            if (d.getCategory().equals(cat)) filteredDeals.add(d);
        }
        adapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.tvCategoryTitle)).setText(cat + " Deals");
    }

    @Override
    public void onDealClick(Deal deal) {
        Intent intent = new Intent(this, DealDetailActivity.class);
        intent.putExtra("title",       deal.getTitle());
        intent.putExtra("store",       deal.getStore());
        intent.putExtra("discount",    deal.getDiscount());
        intent.putExtra("description", deal.getDescription());
        intent.putExtra("category",    deal.getCategory());
        intent.putExtra("url",         deal.getUrl());
        startActivity(intent);
    }
}
