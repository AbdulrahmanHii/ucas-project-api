package com.example.myapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapiapplication.Adapters.SendTokenInterface;
import com.example.myapiapplication.Customer.CusSelectServiceFragment;
import com.example.myapiapplication.ServiceProvider.ProviderServiceFragment;
import com.example.myapiapplication.databinding.ActivityViewBagerProviderBinding;
import com.example.myapiapplication.databinding.FragmentProviderServiceBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ViewBagerProviderActivity extends AppCompatActivity  {
ActivityViewBagerProviderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBagerProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_provider_container, ProviderServiceFragment.newInstance("")).commit();

        binding.homeProviderNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.service:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_provider_container, ProviderServiceFragment.newInstance("")).commit();
                        break;
                    case R.id.orders:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_provider_container, baggerProviderDetailsFragment.newInstance("","")).commit();
                        break;
                }
                return true;
            }
        });

    }

}