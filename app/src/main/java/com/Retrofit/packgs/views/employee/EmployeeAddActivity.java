package com.Retrofit.packgs.views.employee;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.EmployeeApiController;
import com.Retrofit.packgs.databinding.ActivityEmployeeAddBinding;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.views.MainActivity;

import java.io.ByteArrayOutputStream;

public class EmployeeAddActivity extends AppCompatActivity implements View.OnClickListener {
     ActivityEmployeeAddBinding binding;
    EmployeeApiController controller = new EmployeeApiController();
    private ActivityResultLauncher<String> permissionResultLauncher;
    private ActivityResultLauncher<Void> cameraResultLauncher;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void initializeView() {
        setupActivityResults();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        binding.imageViewAddEmp.setOnClickListener(this);
        binding.buSaveEmployee.setOnClickListener(this);

    }

    private void launchPermissionRequest(){
        permissionResultLauncher.launch(Manifest.permission.CAMERA);
    }

    private void setupActivityResults(){
        permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                    cameraResultLauncher.launch(null);
                }
            }
        });
        cameraResultLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null){
                    imageBitmap = result;
                    binding.imageViewAddEmp.setImageBitmap(imageBitmap);
                }
            }
        });
    }
    private byte[] bitmapToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void createEmployee() {
        controller.createEmployee(getEmployee(), new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(EmployeeAddActivity.this, message, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(EmployeeAddActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Employee getEmployee (){
        Employee employee = new Employee();
        employee.name = binding.edFullNameAddEmp.getEditText().getText().toString();
        employee.mobile = binding.edNumberEmp.getEditText().getText().toString();
        employee.nationalNumber = binding.edIdentyEmp.getEditText().getText().toString();
        employee.imagesByteArray = bitmapToBytes();
        return  employee;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewAddEmp){
            launchPermissionRequest();

        }else if (v.getId() == R.id.buSaveEmployee){
            createEmployee();
        }
    }

}