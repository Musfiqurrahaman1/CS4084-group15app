package com.example.group15app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etPassword, etStudentId, etExpiryDate;
    Spinner spinnerCollege;
    Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName   = findViewById(R.id.etFullName);
        etEmail      = findViewById(R.id.etEmail);
        etPassword   = findViewById(R.id.etPassword);
        etStudentId  = findViewById(R.id.etStudentId);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        spinnerCollege = findViewById(R.id.spinnerCollege);
        btnRegister  = findViewById(R.id.btnRegister);
        tvLogin      = findViewById(R.id.tvLogin);

        // Set up college spinner
        List<String> colleges = getCollegeList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, colleges);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCollege.setAdapter(adapter);

        // Expiry date hint
        etExpiryDate.setHint("Card expiry (MM/YYYY)");

        btnRegister.setOnClickListener(v -> {
            if (validateForm()) {
                Toast.makeText(this, "Registered successfully! Welcome to DealMate 🎓",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });

        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateForm() {
        String name     = etFullName.getText().toString().trim();
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String studentId = etStudentId.getText().toString().trim();
        String expiry   = etExpiryDate.getText().toString().trim();
        String college  = spinnerCollege.getSelectedItem().toString();

        if (name.isEmpty()) {
            etFullName.setError("Full name is required"); return false;
        }
        if (email.isEmpty() || !email.contains("@")) {
            etEmail.setError("Valid email is required"); return false;
        }
        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters"); return false;
        }
        if (college.equals("Select your college")) {
            Toast.makeText(this, "Please select your college", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (studentId.isEmpty() || studentId.length() < 5) {
            etStudentId.setError("Enter a valid student ID (min 5 characters)"); return false;
        }
        if (!isValidExpiry(expiry)) {
            etExpiryDate.setError("Enter valid expiry MM/YYYY and card must not be expired");
            return false;
        }
        return true;
    }

    private boolean isValidExpiry(String expiry) {
        if (!expiry.matches("(0[1-9]|1[0-2])/\\d{4}")) return false;
        String[] parts = expiry.split("/");
        int month = Integer.parseInt(parts[0]);
        int year  = Integer.parseInt(parts[1]);
        Calendar now = Calendar.getInstance();
        int currentYear  = now.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH) + 1;
        if (year < currentYear) return false;
        if (year == currentYear && month < currentMonth) return false;
        return true;
    }

    private List<String> getCollegeList() {
        List<String> list = new ArrayList<>();
        list.add("Select your college");
        // Ireland
        list.add("🇮🇪 University of Limerick (UL)");
        list.add("🇮🇪 University College Dublin (UCD)");
        list.add("🇮🇪 Trinity College Dublin (TCD)");
        list.add("🇮🇪 Dublin City University (DCU)");
        list.add("🇮🇪 University College Cork (UCC)");
        list.add("🇮🇪 NUI Galway (NUIG)");
        list.add("🇮🇪 Maynooth University");
        list.add("🇮🇪 TU Dublin");
        list.add("🇮🇪 Munster Technological University (MTU)");
        list.add("🇮🇪 Atlantic Technological University (ATU)");
        list.add("🇮🇪 South East Technological University (SETU)");
        list.add("🇮🇪 RCSI University of Medicine");
        list.add("🇮🇪 National College of Ireland (NCI)");
        list.add("🇮🇪 Griffith College Dublin");
        list.add("🇮🇪 Institute of Technology Carlow");
        list.add("🇮🇪 Dundalk Institute of Technology (DKIT)");
        list.add("🇮🇪 Letterkenny Institute of Technology (LYIT)");
        list.add("🇮🇪 IT Sligo");
        list.add("🇮🇪 Cork Institute of Technology (CIT)");
        list.add("🇮🇪 Dublin Business School (DBS)");
        // UK
        list.add("🇬🇧 University of Oxford");
        list.add("🇬🇧 University of Cambridge");
        list.add("🇬🇧 Imperial College London");
        list.add("🇬🇧 University College London (UCL)");
        list.add("🇬🇧 King's College London (KCL)");
        list.add("🇬🇧 University of Edinburgh");
        list.add("🇬🇧 University of Manchester");
        list.add("🇬🇧 University of Birmingham");
        list.add("🇬🇧 University of Bristol");
        list.add("🇬🇧 University of Warwick");
        list.add("🇬🇧 University of Leeds");
        list.add("🇬🇧 University of Sheffield");
        list.add("🇬🇧 University of Nottingham");
        list.add("🇬🇧 University of Southampton");
        list.add("🇬🇧 University of Glasgow");
        list.add("🇬🇧 Queen's University Belfast (QUB)");
        list.add("🇬🇧 Ulster University");
        list.add("🇬🇧 University of Liverpool");
        list.add("🇬🇧 Newcastle University");
        list.add("🇬🇧 Cardiff University");
        list.add("🇬🇧 University of Bath");
        list.add("🇬🇧 Loughborough University");
        list.add("🇬🇧 Durham University");
        list.add("🇬🇧 University of Exeter");
        list.add("🇬🇧 London School of Economics (LSE)");
        list.add("🇬🇧 Queen Mary University of London");
        list.add("🇬🇧 Heriot-Watt University");
        list.add("🇬🇧 University of Strathclyde");
        list.add("🇬🇧 Coventry University");
        list.add("🇬🇧 University of the Arts London");
        return list;
    }
}
