package com.example.gps_tracking;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView txtSignin;
    private EditText edtFullName, edtEmail, edtPassword, edtConfirmPassword;
    private CheckBox chkTerms;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnBack = findViewById(R.id.btnBack);
        txtSignin = findViewById(R.id.txtSignin);
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        chkTerms = findViewById(R.id.chkTerms);
        btnSignup = findViewById(R.id.btnSignup);

        String text = "Have an Account? Sign in here";
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(0xFF0F2DDE), 18, text.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtSignin.setText(spannable);

        btnBack.setOnClickListener(view -> finish());
        txtSignin.setOnClickListener(view -> finish());

        edtFullName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && edtFullName.getText().toString().trim().isEmpty()) {
                edtFullName.setError("Full Name is required");
            }
        });

        edtEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && edtEmail.getText().toString().trim().isEmpty()) {
                edtEmail.setError("Email is required");
            }
        });

        edtPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && edtPassword.getText().toString().trim().isEmpty()) {
                edtPassword.setError("Password is required");
            }
        });

        edtConfirmPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && edtConfirmPassword.getText().toString().trim().isEmpty()) {
                edtConfirmPassword.setError("Confirm Password is required");
            }
        });

        btnSignup.setOnClickListener(view -> {
            String fullName = edtFullName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(Signup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!chkTerms.isChecked()) {
                Toast.makeText(Signup.this, "You must accept the terms", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(Signup.this, "Signup successful!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
