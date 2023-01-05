package com.example.myapiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapiapplication.databinding.Activity3Binding;

public class Activity3 extends AppCompatActivity {

    Activity3Binding binding;
    Animation bottom_animation,middleAnimation,top_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.midel_animation);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        binding.imageA3.setAnimation(middleAnimation);
        binding.btnA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    }
