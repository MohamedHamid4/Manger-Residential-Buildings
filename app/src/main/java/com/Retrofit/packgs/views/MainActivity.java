package com.Retrofit.packgs.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.AuthApiController;
import com.Retrofit.packgs.databinding.ActivityMainBinding;
import com.Retrofit.packgs.views.Categories.CategoriesMoneyActivity;
import com.Retrofit.packgs.views.pop.PopActivity;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.views.employee.EmployeeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void logout (){
        AuthApiController controller = new AuthApiController();
        controller.logout(new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeView() {
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.cardViewPop.setOnClickListener(this);
        binding.cardViewEmp.setOnClickListener(this);
        binding.cvFinanceCategories.setOnClickListener(this);
        binding.cvManage.setOnClickListener(this);
        binding.Advertising.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cardViewPop) {
            Intent intent = new Intent(getApplicationContext(), PopActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.cardViewEmp) {
            Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.cvFinanceCategories) {
            Intent intent = new Intent(getApplicationContext(), CategoriesMoneyActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.cvManage) {
            Intent intent = new Intent(getApplicationContext(), OperationsActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.Advertising) {
            Intent intent = new Intent(getApplicationContext(), Advertising.class);
            startActivity(intent);
        }
    }

}