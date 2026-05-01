package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etName    = findViewById(R.id.etFullName);
        EditText etEmail   = findViewById(R.id.etEmail);
        EditText etPass    = findViewById(R.id.etPassword);
        EditText etId      = findViewById(R.id.etStudentId);
        Spinner  spinner   = findViewById(R.id.spinnerCollege);
        Button   btnReg    = findViewById(R.id.btnRegister);
        TextView tvLogin   = findViewById(R.id.tvLogin);

        List<String> colleges = getColleges();
        ArrayAdapter<String> a = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colleges);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);

        btnReg.setOnClickListener(v -> {
            if (etName.getText().toString().trim().isEmpty()) {
                etName.setError("Required"); return;
            }
            if (!etEmail.getText().toString().contains("@")) {
                etEmail.setError("Valid email required"); return;
            }
            if (etPass.getText().toString().length() < 6) {
                etPass.setError("Min 6 characters"); return;
            }
            if (spinner.getSelectedItem().toString().equals("Select your college")) {
                Toast.makeText(this, "Please select your college", Toast.LENGTH_SHORT).show(); return;
            }
            if (etId.getText().toString().trim().length() < 5) {
                etId.setError("Enter a valid student ID"); return;
            }
            Toast.makeText(this, "Welcome to DealMate!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        tvLogin.setOnClickListener(v -> finish());
    }

    private List<String> getColleges() {
        List<String> l = new ArrayList<>();
        l.add("Select your college");
        l.add("University of Limerick (UL)");
        l.add("University College Dublin (UCD)");
        l.add("Trinity College Dublin (TCD)");
        l.add("Dublin City University (DCU)");
        l.add("University College Cork (UCC)");
        l.add("NUI Galway (NUIG)");
        l.add("Maynooth University");
        l.add("TU Dublin");
        l.add("Munster Technological University (MTU)");
        l.add("Atlantic Technological University (ATU)");
        l.add("South East Technological University (SETU)");
        l.add("RCSI University of Medicine");
        l.add("National College of Ireland (NCI)");
        l.add("Griffith College Dublin");
        l.add("Dundalk Institute of Technology (DKIT)");
        l.add("Letterkenny Institute of Technology (LYIT)");
        l.add("IT Sligo");
        l.add("Cork Institute of Technology (CIT)");
        l.add("Dublin Business School (DBS)");
        l.add("Institute of Technology Carlow");
        return l;
    }
}
