package com.Retrofit.packgs.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.databinding.ActivitySplashBinding;
import com.Retrofit.packgs.prefs.AppShedPreferencesController;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    private static int DELAY_TIME = 4000;

    Animation topAnim,bottomAnim;
    ImageView imageView;
    TextView appname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imageView =  findViewById(R.id.imageView2);
        appname =  findViewById(R.id.appname);

        imageView.setAnimation(topAnim);
        appname.setAnimation(bottomAnim);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handelSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void handelSplashActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = AppShedPreferencesController.getInstance().isLoggedIn();
                Intent intent = new Intent(SplashActivity.this, isLoggedIn ? MainActivity.class : LoginActivity.class);
                startActivity(intent);
            }
        },DELAY_TIME);
    }
}