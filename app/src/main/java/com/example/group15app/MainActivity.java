package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DealAdapter.OnDealClickListener {

    RecyclerView recyclerView;
    DealAdapter adapter;
    List<Deal> allDeals;
    List<Deal> filteredDeals;
    EditText searchBar;
    ChipGroup chipGroup;
    String selectedCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar    = findViewById(R.id.searchBar);
        chipGroup    = findViewById(R.id.chipGroup);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allDeals      = getHardcodedDeals();
        filteredDeals = new ArrayList<>(allDeals);
        adapter       = new DealAdapter(filteredDeals, this);
        recyclerView.setAdapter(adapter);

        // Search
        searchBar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            public void onTextChanged(CharSequence s, int st, int b, int c) { filterDeals(); }
            public void afterTextChanged(Editable s) {}
        });

        // Category chips
        setupChips();

        // Bottom nav
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_categories) {
                startActivity(new Intent(this, CategoriesActivity.class));
                return true;
            } else if (id == R.id.nav_favourites) {
                startActivity(new Intent(this, FavouritesActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void setupChips() {
        String[] categories = {"All", "Food", "Coffee", "Fitness", "Tech", "Transport", "Entertainment", "Beauty"};
        for (String cat : categories) {
            Chip chip = new Chip(this);
            chip.setText(cat);
            chip.setCheckable(true);
            chip.setChecked(cat.equals("All"));
            chip.setChipBackgroundColorResource(R.color.chip_color);
            chip.setOnClickListener(v -> {
                selectedCategory = cat;
                filterDeals();
            });
            chipGroup.addView(chip);
        }
    }

    private void filterDeals() {
        String query = searchBar.getText().toString().toLowerCase().trim();
        filteredDeals.clear();
        for (Deal d : allDeals) {
            boolean matchesSearch   = d.getTitle().toLowerCase().contains(query)
                                   || d.getStore().toLowerCase().contains(query);
            boolean matchesCategory = selectedCategory.equals("All")
                                   || d.getCategory().equals(selectedCategory);
            if (matchesSearch && matchesCategory) filteredDeals.add(d);
        }
        adapter.notifyDataSetChanged();
        if (filteredDeals.isEmpty()) {
            Toast.makeText(this, "No deals found", Toast.LENGTH_SHORT).show();
        }
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

    public static List<Deal> getHardcodedDeals() {
        List<Deal> deals = new ArrayList<>();
        deals.add(new Deal("10% Student Discount",  "Subway Limerick",      "10% OFF",   "Show your student ID at the counter to get 10% off any footlong or salad.",                              "Food",          "https://www.subway.com/en-IE"));
        deals.add(new Deal("Student Meal Deal",     "Domino's Pizza",       "20% OFF",   "Order online using code STUDENT20 at checkout. Valid on all pizzas.",                                     "Food",          "https://www.dominos.ie"));
        deals.add(new Deal("Free Coffee Monday",    "Insomnia Coffee",      "FREE",      "Get a free small coffee every Monday with a valid student card. No purchase needed.",                     "Coffee",        "https://www.insomnia.ie"));
        deals.add(new Deal("Gym Membership Deal",   "UL Sport Arena",       "30% OFF",   "Student rate on full gym membership. Valid with UL student ID only.",                                     "Fitness",       "https://www.ulsport.ie"));
        deals.add(new Deal("Laptop Student Price",  "Currys PC World",      "15% OFF",   "Bring your student ID for 15% off laptops and accessories in-store.",                                     "Tech",          "https://www.currys.ie"));
        deals.add(new Deal("Bus Pass Discount",     "Bus Éireann Limerick", "50% OFF",   "Student Leap Card gives 50% off all Bus Éireann routes. Apply at the Student Union.",                    "Transport",     "https://www.buseireann.ie"));
        deals.add(new Deal("Cinema Tuesday Deal",   "Omniplex Limerick",    "€6 Ticket", "Every Tuesday students pay just €6. Show ID at the box office.",                                         "Entertainment", "https://www.omniplex.ie"));
        deals.add(new Deal("Haircut Discount",      "Rush Hair Limerick",   "25% OFF",   "25% off all cuts and styles for students. Book online and mention STUDENT25.",                            "Beauty",        "https://www.rushhair.com"));
        deals.add(new Deal("Student Coffee Card",   "Costa Coffee",         "10% OFF",   "Flash your student card for 10% off every drink at any Costa Coffee branch.",                            "Coffee",        "https://www.costacoffee.ie"));
        deals.add(new Deal("Tech Student Bundle",   "Apple Education",      "Up to €200 off", "Buy a Mac or iPad with Apple's education pricing. Available online with college email.",            "Tech",          "https://www.apple.com/ie/education/"));
        deals.add(new Deal("Student Eats Deal",     "Boojum Burrito",       "15% OFF",   "Get 15% off your burrito bowl with a valid student card at any Boojum location.",                        "Food",          "https://www.boojummex.com"));
        deals.add(new Deal("Fitness First Student", "PureGym Limerick",     "40% OFF",   "Monthly student membership at a discounted rate. No joining fee in September.",                          "Fitness",       "https://www.puregym.com/ireland/"));
        return deals;
    }
}
