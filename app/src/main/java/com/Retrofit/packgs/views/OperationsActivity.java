package com.Retrofit.packgs.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.Retrofit.packgs.Adapter.EmployeeViewAdapter;
import com.Retrofit.packgs.Adapter.OperationAdapter;
import com.Retrofit.packgs.Adapter.OperationViewHolder;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.ApiController;
import com.Retrofit.packgs.databinding.ActivityOperationsBinding;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.model.FinanceCategory;
import com.Retrofit.packgs.model.Operation;
import com.Retrofit.packgs.model.OperationsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperationsActivity extends AppCompatActivity {

    ActivityOperationsBinding activityOperationsBinding;
    OperationViewHolder adapter;
    ArrayList<Operation> operations = new ArrayList<>();
    OperationAdapter operationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        operationAdapter = new OperationAdapter(operations);
        activityOperationsBinding = ActivityOperationsBinding.inflate(getLayoutInflater(),null,false);
        setContentView(activityOperationsBinding.getRoot());

        activityOperationsBinding.rvOperations.setLayoutManager(new LinearLayoutManager(this));
        activityOperationsBinding.rvOperations.setAdapter(operationAdapter);

        ApiController.getInstance().getRetrofitRequest().getOperations().enqueue(new Callback<OperationsResponse>() {
            @Override
            public void onResponse(Call<OperationsResponse> call, Response<OperationsResponse> response) {
                if(response.body().isStatus()){
                    operations.addAll(response.body().getOperations());
                    operationAdapter.notifyDataSetChanged();
                }else {

                }
            }

            @Override
            public void onFailure(Call<OperationsResponse> call, Throwable t) {

            }
        });

        activityOperationsBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OperationsActivity.this,AddOperationActivity.class));
            }
        });

    }

/*    public void setAdapter() {
        adapter = new EmployeeViewAdapter(operation);
        activityOperationsBinding.rvOperations.setAdapter(adapter);
        adapter.setCallBack(this::onActionCallBack);
        activityOperationsBinding.rvOperations.setLayoutManager(new LinearLayoutManager(this));
    }*/

}