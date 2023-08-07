package com.Retrofit.packgs.views.pop;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.Retrofit.packgs.api.controllers.PopApiController;
import com.Retrofit.packgs.databinding.ActivityPopAddBinding;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.model.Pop;

import java.io.ByteArrayOutputStream;
public class PopAddActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPopAddBinding binding;
    PopApiController userApiController = new PopApiController();
    private String gender ;
    private ActivityResultLauncher<String> permissionResultLauncher;
    private ActivityResultLauncher<Void> cameraResultLauncher;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }
    private void initializeView(){
        controlGenderSelection();
        setOnClickListener();
        setupActivityResults();
    }
    private void addUser(){
        userApiController = new PopApiController();
        userApiController.addUser(getUser(), new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(PopAddActivity.this, message, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(PopAddActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void setOnClickListener(){
        binding.PareonAddPop.setOnClickListener(this);
        binding.buSavePopAdd.setOnClickListener(this);
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
                    binding.PareonAddPop.setImageBitmap(imageBitmap);

                }
            }
        });
    }
    private byte[] bitmapToBytes() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private void controlGenderSelection (){
        binding.groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender =  checkedId == R.id.meal_radio ? "M" : "F";
            }
        });
    }

    private Pop getUser() {
        Pop user = new Pop();
        user.email = binding.emailUserEditText.getEditText().getText().toString().trim();
        user.name = binding.nameUserEditText.getEditText().getText().toString().trim();
        user.familyMembers = binding.familyMembersUserEditText.getEditText().getText().toString().trim();
        user.mobile = binding.mobileUserEditText.getEditText().getText().toString().trim();
        user.gender = gender;
        user.nationalNumber = binding.nationalUserEditText.getEditText().getText().toString().trim();
        user.imagesByteArray = bitmapToBytes();
        return user;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.PareonAddPop){
            launchPermissionRequest();
        }else if (v.getId() == R.id.buSavePopAdd){
            addUser();
        }
    }
}