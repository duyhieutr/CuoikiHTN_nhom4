package com.example.gps_tracking;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView txtLogin, txtError;
    private CheckBox chkKeepSignedIn;

    private final String correctEmail = "hieutran.28042004@gmail.com";
    private final String correctPassword = "123456";

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtLogin = findViewById(R.id.txtLogin);
        txtError = findViewById(R.id.txtError);
        chkKeepSignedIn = findViewById(R.id.chkKeepSignedIn);

        String text = "Chưa có tài khoản? Đăng ký tại đây";
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new android.text.style.ForegroundColorSpan(0xFF0F2DDE), 23, text.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtLogin.setText(spannable);

        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });

        edtPassword.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (edtPassword.getRight() - edtPassword.getCompoundDrawables()[2].getBounds().width())) {
                    isPasswordVisible = !isPasswordVisible;
                    if(isPasswordVisible) {
                        edtPassword.setTransformationMethod(null);
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_visibility), null);
                    } else {
                        edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.ic_visibility), null);
                    }
                    edtPassword.setSelection(edtPassword.getText().length());
                    return true;
                }
            }
            return false;
        });

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()) {
                txtError.setText("Không được bỏ trống");
                txtError.setVisibility(TextView.VISIBLE);
                return;
            }

            if(email.equals(correctEmail) && password.equals(correctPassword)) {
                txtError.setVisibility(TextView.GONE);
                Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                txtError.setText("Sai email hoặc mật khẩu");
                txtError.setVisibility(TextView.VISIBLE);
            }
        });
    }
}
