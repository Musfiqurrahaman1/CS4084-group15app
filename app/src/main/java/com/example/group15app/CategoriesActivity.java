package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.*;

public class CategoriesActivity extends AppCompatActivity implements DealAdapter.OnDealClickListener {

    RecyclerView rv;
    DealAdapter adapter;
    List<Deal> filtered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        rv = findViewById(R.id.rvCategoryDeals);
        rv.setLayoutManager(new LinearLayoutManager(this));
        filtered = new ArrayList<>();
        adapter  = new DealAdapter(filtered, this);
        rv.setAdapter(adapter);

        int[] ids = {R.id.btnCatFood, R.id.btnCatCoffee, R.id.btnCatFitness,
                     R.id.btnCatTech, R.id.btnCatTransport, R.id.btnCatEntertainment, R.id.btnCatBeauty};
        String[] cats = {"Food","Coffee","Fitness","Tech","Transport","Entertainment","Beauty"};

        for (int i = 0; i < ids.length; i++) {
            final String cat = cats[i];
            findViewById(ids[i]).setOnClickListener(v -> loadCategory(cat));
        }

        loadCategory("Food");
        findViewById(R.id.btnBackCategories).setOnClickListener(v -> finish());
    }

    private void loadCategory(String cat) {
        filtered.clear();
        for (Deal d : DataManager.getDeals())
            if (d.getCategory().equals(cat)) filtered.add(d);
        adapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.tvCategoryTitle)).setText(cat + " Deals");
    }

    @Override
    public void onDealClick(Deal deal) {
        Intent i = new Intent(this, DealDetailActivity.class);
        i.putExtra("title", deal.getTitle()); i.putExtra("store", deal.getStore());
        i.putExtra("discount", deal.getDiscount()); i.putExtra("description", deal.getDescription());
        i.putExtra("category", deal.getCategory()); i.putExtra("url", deal.getUrl());
        i.putExtra("address", deal.getAddress()); i.putExtra("eircode", deal.getEircode());
        startActivity(i);
    }
}
