package com.Retrofit.packgs.views;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.Adapter.AdvertisingViewAdapter;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.AdvertisingApiController;
import com.Retrofit.packgs.databinding.ActivityAdvertisingBinding;
import com.Retrofit.packgs.dialoge.AdvertisingFragment;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.AdMangment;
import com.Retrofit.packgs.views.employee.EmployeeAddActivity;
import java.util.ArrayList;
import java.util.List;

public class Advertising extends AppCompatActivity implements TaskCallBack, AdvertisingFragment.OnPositiveClickListener, View.OnClickListener{
    ActivityAdvertisingBinding binding;
    List<AdMangment> adMangments = new ArrayList<>();
    AdvertisingViewAdapter adapter;
    AdvertisingApiController controller;
    AdMangment adMangment;
    int id;
    int position;
    String title;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdvertisingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Advertising.this, AddAdvertising.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    public void initializeView() {
        setAdapter();
        getAdvertising();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.floatingActionButton.setOnClickListener(this);
    }

    public void setAdapter() {
        adapter = new AdvertisingViewAdapter(adMangments);
        binding.rvAdvertising.setAdapter(adapter);
        adapter.setCallBack(this::onActionCallBack);
        binding.rvAdvertising.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAdvertising() {
        controller = new AdvertisingApiController();
        controller.getAdvertising(new ListCalBack<AdMangment>() {
            @Override
            public void onSuccess(List<AdMangment> list) {
                adMangments.clear();
                adMangments.addAll(list);
                adapter.notifyItemChanged(0, adMangments.size());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onActionCallBack(ActionType actionType, int positon, int id) {
        if (actionType == ActionType.delete) {
            adMangments.remove(positon);
            adapter.notifyItemRemoved(positon);
            this.position = positon;
            this.id = id;
            deleteAdvertising();
        } else if (actionType == ActionType.update) {
            AdvertisingFragment fragment = AdvertisingFragment.newInstance("Update Advertising");
            fragment.show(getSupportFragmentManager(), null);
            this.id = id;
        }
    }

    private void updateAdvertising(AdMangment adMangment) {
        this.adMangment = adMangment;
        controller = new AdvertisingApiController();
        adMangment.id = id;
        adMangment.title = title;
        adMangment.info = info;
        controller.updateAdvertising(adMangment, new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

            @Override
            public void onFailure(String message) {
                Log.e("onFailure", "onFailure: " + message);
            }
        });
    }

    private void deleteAdvertising() {
        controller = new AdvertisingApiController();
        AdMangment adMangment1 = new AdMangment();
        adMangment1.id = (id);
        controller.deleteAdvertising(adMangment1, new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(Advertising.this, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(Advertising.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPositiveButtOnClick(String title, String info) {
        this.title = title;
        this.info = info;
        updateAdvertising(adMangments.get(position));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingActionButton){
            Intent intent = new Intent(getApplicationContext(), AddAdvertising.class);
            startActivity(intent);


        }
    }

}