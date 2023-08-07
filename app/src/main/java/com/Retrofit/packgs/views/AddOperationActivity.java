package com.Retrofit.packgs.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.databinding.ActivityAddOperationBinding;
import com.Retrofit.packgs.model.BaseResponse;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.model.FinanceCategory;
import com.Retrofit.packgs.model.Pop;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOperationActivity extends AppCompatActivity {

    int selectedCategory = 1;
    ActivityAddOperationBinding activityAddOperationBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddOperationBinding = ActivityAddOperationBinding.inflate(getLayoutInflater(),null,false);
        setContentView(activityAddOperationBinding.getRoot());
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Pop> users = new ArrayList<>();

        activityAddOperationBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FinanceCategory financeCategory = (FinanceCategory) activityAddOperationBinding.spinner.getSelectedItem();
                selectedCategory = financeCategory.getId();

                if(selectedCategory == 1){
                    activityAddOperationBinding.spinner2.setVisibility(View.VISIBLE);
                    activityAddOperationBinding.spinner3.setVisibility(View.GONE);
                }else if(selectedCategory == 2){
                    activityAddOperationBinding.spinner2.setVisibility(View.GONE);
                    activityAddOperationBinding.spinner3.setVisibility(View.VISIBLE);
                }else{
                    activityAddOperationBinding.spinner2.setVisibility(View.GONE);
                    activityAddOperationBinding.spinner3.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ApiController.getInstance().getRetrofitRequest().getEmployee().enqueue(new Callback<BaseResponse<Employee>>() {
            @Override
            public void onResponse(Call<BaseResponse<Employee>> call, Response<BaseResponse<Employee>> response) {
                employees.addAll(response.body().list);
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddOperationActivity.this, android.R.layout.simple_list_item_1,employees);
                activityAddOperationBinding.spinner3.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<BaseResponse<Employee>> call, Throwable t) {

            }
        });
        ApiController.getInstance().getRetrofitRequest().getUsers().enqueue(new Callback<BaseResponse<Pop>>() {
            @Override
            public void onResponse(Call<BaseResponse<Pop>> call, Response<BaseResponse<Pop>> response) {
                users.addAll(response.body().list);
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddOperationActivity.this, android.R.layout.simple_list_item_1,users);
                activityAddOperationBinding.spinner2.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<BaseResponse<Pop>> call, Throwable t) {

            }
        });
        FinanceCategory financeCategory1 = new FinanceCategory(1,"خدمات السكان","0","0");
        FinanceCategory financeCategory2 = new FinanceCategory(2,"رواتب","0","0");
        FinanceCategory financeCategory3 = new FinanceCategory(3,"مشتريات","0","0");
        FinanceCategory financeCategory4 = new FinanceCategory(4,"صيانة","0","0");


        ArrayList<FinanceCategory> financeCategories = new ArrayList<>();
        financeCategories.add(financeCategory1);
        financeCategories.add(financeCategory2);
        financeCategories.add(financeCategory3);
        financeCategories.add(financeCategory4);
        ArrayAdapter arrayAdapter = new ArrayAdapter(AddOperationActivity.this, android.R.layout.simple_list_item_1,financeCategories);
        activityAddOperationBinding.spinner.setAdapter(arrayAdapter);


        activityAddOperationBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String actor_id = "";
                String actorType = "";
                if(selectedCategory == 1){
                    actor_id = ((Pop) activityAddOperationBinding.spinner2.getSelectedItem()).id+"";
                    actorType = "Resident";
                }
                if(selectedCategory == 2){
                    actor_id = ((Employee) activityAddOperationBinding.spinner3.getSelectedItem()).id+"";
                    actorType = "Employee";

                }
                Log.v("ttt","category_id ::" +selectedCategory);

                Map<String,String> hashMap = new HashMap<>();
                hashMap.put("category_id", String.valueOf(selectedCategory));
                hashMap.put("amount",activityAddOperationBinding.edAmount.getText().toString());
                hashMap.put("details",activityAddOperationBinding.edDetailsText.getText().toString());
                if(selectedCategory == 1||selectedCategory ==2) {
                    hashMap.put("actor_id", actor_id);
                    hashMap.put("actor_type", actorType);
                }
                hashMap.put("date",activityAddOperationBinding.edDateText.getText().toString());
                ApiController.getInstance().getRetrofitRequest().addOperation(hashMap).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            Log.v("ttt",response.body().message);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.v("ttt",t.getLocalizedMessage());

                    }
                });
            }
        });



    }
}