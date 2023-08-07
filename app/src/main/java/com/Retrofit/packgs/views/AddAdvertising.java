package com.Retrofit.packgs.views;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.api.controllers.AdvertisingApiController;
import com.Retrofit.packgs.databinding.ActivityAddAdvertisingBinding;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.model.AdMangment;

import java.io.ByteArrayOutputStream;

public class AddAdvertising extends AppCompatActivity implements View.OnClickListener{
    ActivityAddAdvertisingBinding binding;
    AdvertisingApiController controller = new AdvertisingApiController();
    private ActivityResultLauncher<String> permissionResultLauncher;
    private ActivityResultLauncher<Void> cameraResultLauncher;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAdvertisingBinding.inflate(getLayoutInflater());
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
        binding.imageViewAddAver.setOnClickListener(this);
        binding.buSaveAdvertising.setOnClickListener(this);

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
                    binding.imageViewAddAver.setImageBitmap(imageBitmap);
                }
            }
        });
    }
    private byte[] bitmapToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void createAdMangment() {
        controller.createAdvertising(getAdMangment(), new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(AddAdvertising.this, message, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(AddAdvertising.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AdMangment getAdMangment (){
        AdMangment adMangment = new AdMangment();
        adMangment.title = binding.edTitle.getEditText().getText().toString();
        adMangment.info = binding.edDescription.getEditText().getText().toString();
        adMangment.imagesByteArray = bitmapToBytes();
        return  adMangment;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewAddAver){
            launchPermissionRequest();

        }else if (v.getId() == R.id.buSaveAdvertising){
            createAdMangment();
        }
    }
}