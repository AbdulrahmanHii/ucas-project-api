package com.example.myapiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.databinding.ScreenSplashBinding;

public class SplashScreen extends AppCompatActivity {

    ScreenSplashBinding binding;
    Animation bottom_animation,middleAnimation,top_animation;
    private static int SPLASH_TIME_OUT = 2000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScreenSplashBinding.inflate(getLayoutInflater());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(Constant.fileName, MODE_PRIVATE);
        if (sharedPreferences!=null){

            boolean checkToken=  sharedPreferences.getBoolean(Constant.HAVE_TOKEN,false);
            if (checkToken){
                Intent intent=new Intent(getApplicationContext(),RegisterUseActivity.class);
                startActivity(intent);
            }
            Toast.makeText(this, "😍Welcome to the in the  first time😍", Toast.LENGTH_SHORT).show();

        }



        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.midel_animation);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);


        binding.splashScreenImage.setAnimation(middleAnimation);
//        binding.splashScreenTv.setAnimation(middleAnimation);



        //Splash Screen Code to call new Activity after some time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, Activity1.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);










    }
}