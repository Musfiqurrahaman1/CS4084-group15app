package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.btnBackProfile).setOnClickListener(v -> finish());

        // Show current user email
        TextView tvEmail = findViewById(R.id.tvUserEmail);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            tvEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

        // Logout button
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finishAffinity();
        });
    }
}
