package com.example.myapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapiapplication.Adapters.SendTokenInterface;
import com.example.myapiapplication.Customer.CusSelectServiceFragment;
import com.example.myapiapplication.databinding.CusActivityServiceBagerViewBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ViewBagerServiceActivityCus extends AppCompatActivity{
    CusActivityServiceBagerViewBinding binding;
    SendTokenInterface tokenInterface;
    String myTokenCos;
    String myTokenReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CusActivityServiceBagerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_customer_navigation, CusSelectServiceFragment.newInstance("")).commit();

        binding.homeCustomerNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.service:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_customer_container, CusSelectServiceFragment.newInstance("")).commit();
                        break;
                    case R.id.orders:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_customer_container, OrdersCusFragmentBager.newInstance("", "")).commit();
                        break;
                }
                return true;
            }
        });


    }

}