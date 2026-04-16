package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.text.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.*;
import java.util.*;

public class MainActivity extends AppCompatActivity implements DealAdapter.OnDealClickListener {

    RecyclerView rv;
    DealAdapter adapter;
    List<Deal> all, filtered;
    EditText searchBar;
    ChipGroup chipGroup;
    String selectedCat = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv        = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        chipGroup = findViewById(R.id.chipGroup);
        rv.setLayoutManager(new LinearLayoutManager(this));

        all      = DataManager.getDeals();
        filtered = new ArrayList<>(all);
        adapter  = new DealAdapter(filtered, this);
        rv.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int i, int c, int a) {}
            public void onTextChanged(CharSequence s, int i, int b, int c) { filterDeals(); }
            public void afterTextChanged(Editable s) {}
        });

        setupChips();

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home)        return true;
            if (id == R.id.nav_categories)  { startActivity(new Intent(this, CategoriesActivity.class)); return true; }
            if (id == R.id.nav_favourites)  { startActivity(new Intent(this, FavouritesActivity.class)); return true; }
            if (id == R.id.nav_map)         { startActivity(new Intent(this, MapActivity.class)); return true; }
            if (id == R.id.nav_profile)     { startActivity(new Intent(this, ProfileActivity.class)); return true; }
            return false;
        });
    }

    private void setupChips() {
        String[] cats = {"All","Food","Coffee","Fitness","Tech","Transport","Entertainment","Beauty"};
        for (String cat : cats) {
            Chip c = new Chip(this);
            c.setText(cat);
            c.setCheckable(true);
            c.setChecked(cat.equals("All"));
            c.setOnClickListener(v -> { selectedCat = cat; filterDeals(); });
            chipGroup.addView(c);
        }
    }

    private void filterDeals() {
        String q = searchBar.getText().toString().toLowerCase().trim();
        filtered.clear();
        for (Deal d : all) {
            boolean ms = d.getTitle().toLowerCase().contains(q) || d.getStore().toLowerCase().contains(q);
            boolean mc = selectedCat.equals("All") || d.getCategory().equals(selectedCat);
            if (ms && mc) filtered.add(d);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDealClick(Deal deal) {
        Intent i = new Intent(this, DealDetailActivity.class);
        i.putExtra("title",       deal.getTitle());
        i.putExtra("store",       deal.getStore());
        i.putExtra("discount",    deal.getDiscount());
        i.putExtra("description", deal.getDescription());
        i.putExtra("category",    deal.getCategory());
        i.putExtra("url",         deal.getUrl());
        i.putExtra("address",     deal.getAddress());
        i.putExtra("eircode",     deal.getEircode());
        startActivity(i);
    }
}
