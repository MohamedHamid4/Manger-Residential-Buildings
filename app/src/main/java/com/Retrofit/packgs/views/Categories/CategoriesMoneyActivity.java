package com.Retrofit.packgs.views.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.databinding.ActivityCategoriesMoneyBinding;
import com.Retrofit.packgs.model.CategoriesResonse;
import com.Retrofit.packgs.model.FinanceCategory;
import com.Retrofit.packgs.views.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesMoneyActivity extends AppCompatActivity {
    ActivityCategoriesMoneyBinding binding ;
    ArrayList<FinanceCategory> financeCategories = new ArrayList<>();
    ApiController apiController = ApiController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesMoneyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CategoryFinanceAdapter categoryFinanceAdapter = new CategoryFinanceAdapter(financeCategories);
        binding.rvCategories.setAdapter(categoryFinanceAdapter);

        ApiController apiController = ApiController.getInstance();
        apiController.getRetrofitRequest().getCategories().enqueue(new Callback<CategoriesResonse>() {
            @Override
            public void onResponse(Call<CategoriesResonse> call, Response<CategoriesResonse> response) {
                Log.v("CategoriesMoney",response.message());

                financeCategories.addAll(response.body().getFinanceCategories());
                categoryFinanceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoriesResonse> call, Throwable t) {

            }
        });
    }
}