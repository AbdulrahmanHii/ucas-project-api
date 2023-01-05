package com.example.myapiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.databinding.Activity1Binding;
import com.example.myapiapplication.databinding.ScreenSplashBinding;

public class Activity1 extends AppCompatActivity {

    Activity1Binding binding;
    Animation bottom_animation,middleAnimation,top_animation;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity1Binding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());



        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.midel_animation);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        binding.imageA1.setAnimation(middleAnimation);
        binding.btnA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity1.this, Activity2.class);
                startActivity(intent);
            }
        });
    }
}