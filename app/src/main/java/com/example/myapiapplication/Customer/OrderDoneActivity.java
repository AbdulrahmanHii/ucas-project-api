package com.example.myapiapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapiapplication.HomeActivity;
import com.example.myapiapplication.R;
import com.example.myapiapplication.ViewBagerProviderActivity;
import com.example.myapiapplication.databinding.ActivityOrderdoneBinding;
import com.example.myapiapplication.databinding.ActivitySmithBinding;

public class OrderDoneActivity extends AppCompatActivity {
    ActivityOrderdoneBinding binding;

    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderdoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.goToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context, HomeActivity.class);
                startActivity(intent1);
            }
        });

        binding.imageCancelOrderDoneA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context, LocationActivity.class);
                startActivity(intent1);
            }
        });






    }
}