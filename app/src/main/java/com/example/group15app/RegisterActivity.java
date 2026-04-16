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
        EditText etExpiry  = findViewById(R.id.etExpiryDate);
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
            if (!isValidExpiry(etExpiry.getText().toString().trim())) {
                etExpiry.setError("Enter valid MM/YYYY and card must not be expired"); return;
            }
            Toast.makeText(this, "Welcome to DealMate!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        tvLogin.setOnClickListener(v -> finish());
    }

    private boolean isValidExpiry(String e) {
        if (!e.matches("(0[1-9]|1[0-2])/\\d{4}")) return false;
        String[] p = e.split("/");
        int m = Integer.parseInt(p[0]), y = Integer.parseInt(p[1]);
        Calendar now = Calendar.getInstance();
        int cm = now.get(Calendar.MONTH) + 1, cy = now.get(Calendar.YEAR);
        return y > cy || (y == cy && m >= cm);
    }

    private List<String> getColleges() {
        List<String> l = new ArrayList<>();
        l.add("Select your college");
        l.add("IE - University of Limerick (UL)");
        l.add("IE - University College Dublin (UCD)");
        l.add("IE - Trinity College Dublin (TCD)");
        l.add("IE - Dublin City University (DCU)");
        l.add("IE - University College Cork (UCC)");
        l.add("IE - NUI Galway (NUIG)");
        l.add("IE - Maynooth University");
        l.add("IE - TU Dublin");
        l.add("IE - Munster Technological University (MTU)");
        l.add("IE - Atlantic Technological University (ATU)");
        l.add("IE - South East Technological University (SETU)");
        l.add("IE - RCSI University of Medicine");
        l.add("IE - National College of Ireland (NCI)");
        l.add("IE - Griffith College Dublin");
        l.add("IE - Dundalk Institute of Technology (DKIT)");
        l.add("IE - Letterkenny Institute of Technology (LYIT)");
        l.add("IE - IT Sligo");
        l.add("IE - Cork Institute of Technology (CIT)");
        l.add("IE - Dublin Business School (DBS)");
        l.add("UK - University of Oxford");
        l.add("UK - University of Cambridge");
        l.add("UK - Imperial College London");
        l.add("UK - University College London (UCL)");
        l.add("UK - King's College London (KCL)");
        l.add("UK - University of Edinburgh");
        l.add("UK - University of Manchester");
        l.add("UK - University of Birmingham");
        l.add("UK - University of Bristol");
        l.add("UK - University of Warwick");
        l.add("UK - University of Leeds");
        l.add("UK - University of Sheffield");
        l.add("UK - University of Nottingham");
        l.add("UK - University of Southampton");
        l.add("UK - University of Glasgow");
        l.add("UK - Queen's University Belfast (QUB)");
        l.add("UK - Ulster University");
        l.add("UK - University of Liverpool");
        l.add("UK - Newcastle University");
        l.add("UK - Cardiff University");
        l.add("UK - University of Bath");
        l.add("UK - Loughborough University");
        l.add("UK - Durham University");
        l.add("UK - University of Exeter");
        l.add("UK - London School of Economics (LSE)");
        l.add("UK - Queen Mary University of London");
        l.add("UK - Heriot-Watt University");
        l.add("UK - University of Strathclyde");
        l.add("UK - Coventry University");
        l.add("UK - University of the Arts London");
        return l;
    }
}
