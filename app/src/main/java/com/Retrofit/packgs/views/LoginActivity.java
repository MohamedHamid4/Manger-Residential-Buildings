package com.Retrofit.packgs.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.AuthApiController;
import com.Retrofit.packgs.databinding.ActivityLoginBinding;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.Admin;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.buSaveLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bu_Save_login) {
            performLogin();
        }
    }

    private void performLogin() {
        login();
    }

    private void login() {
        AuthApiController authApiController = new AuthApiController();
        authApiController.login(getDataLogIn(), new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String message) {
                //TODO: must show error message
                Log.d("Retrofit", "onFailure: ");
                Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private Admin getDataLogIn() {
        Admin admin = new Admin();
        admin.email = binding.edEmailLogin.getText().toString().trim();
        admin.password = binding.edPasswordLogin.getText().toString().trim();
        return admin;
    }

}