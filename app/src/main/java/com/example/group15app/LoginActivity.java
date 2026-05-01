package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // If already logged in go straight to home
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        EditText etEmail    = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button   btnLogin   = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvRegister);
        ProgressBar progressBar = new ProgressBar(this);

        btnLogin.setOnClickListener(v -> {
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || !email.contains("@")) {
                etEmail.setError("Valid email required");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Min 6 characters");
                return;
            }

            btnLogin.setEnabled(false);
            btnLogin.setText("Logging in...");

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        btnLogin.setEnabled(true);
                        btnLogin.setText("Log In");
                        Toast.makeText(this, "Login failed: " + 
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        });

        tvRegister.setOnClickListener(v ->
            startActivity(new Intent(this, RegisterActivity.class)));
    }
}
