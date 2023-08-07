package com.Retrofit.packgs.views.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.databinding.ActivityEmployeeBinding;
import com.Retrofit.packgs.dialoge.DialogFragment;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.Adapter.EmployeeViewAdapter;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.EmployeeApiController;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements TaskCallBack, DialogFragment.OnPositiveClickListener, View.OnClickListener {
    ActivityEmployeeBinding binding;
    List<Employee> employees = new ArrayList<>();
    EmployeeViewAdapter adapter;
    EmployeeApiController controller;
    Employee employee;
    int id;
    int position;
    String name;
    String phone;
    String national;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    public void initializeView() {
        setAdapter();
        getEmployee();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.floatingActionButton.setOnClickListener(this);

    }


    public void setAdapter() {
        adapter = new EmployeeViewAdapter(employees);
        binding.rv.setAdapter(adapter);
        adapter.setCallBack(this::onActionCallBack);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getEmployee() {
        controller = new EmployeeApiController();
        controller.getEmployee(new ListCalBack<Employee>() {
            @Override
            public void onSuccess(List<Employee> list) {
                employees.clear();
                employees.addAll(list);
                adapter.notifyItemChanged(0, employees.size());
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void onActionCallBack(ActionType actionType, int positon, int id) {
        if (actionType == ActionType.delete) {
            employees.remove(positon);
            adapter.notifyItemRemoved(positon);
            this.position = positon;
            this.id = id;
            deleteEmployee();
        } else if (actionType == ActionType.update) {
            DialogFragment fragment = DialogFragment.newInstance("Update Employee");
            fragment.show(getSupportFragmentManager(), null);
            this.id = id;
        }
    }

    private void updateEmployee(Employee employee) {
        this.employee = employee;
        controller = new EmployeeApiController();
        employee.id = id;
        employee.name = name;
        employee.mobile = phone;
        employee.nationalNumber = national;
        controller.update(employee, new ProcessCallBack() {
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

    private void deleteEmployee() {
        controller = new EmployeeApiController();
        Employee employee = new Employee();
        employee.id = (id);
        controller.delete(employee, new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(EmployeeActivity.this, message, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(EmployeeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPositiveButtOnClicked(String name, String phone, String national) {
        this.name = name;
        this.phone = phone;
        this.national = national;
        updateEmployee(employees.get(position));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingActionButton){
            Intent intent = new Intent(getApplicationContext(), EmployeeAddActivity.class);
            startActivity(intent);


        }
    }
}