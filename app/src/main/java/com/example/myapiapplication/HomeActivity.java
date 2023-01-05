package com.example.myapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapiapplication.Adapters.BagerFragmentsAdapter;
import com.example.myapiapplication.Adapters.ViewBaggerClass;
import com.example.myapiapplication.Customer.CustomerFragmentLogin;
import com.example.myapiapplication.ServiceProvider.ServiceProviderFragmentLogin;
import com.example.myapiapplication.databinding.ActivityHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    Animation bottom_animation,middleAnimation,top_animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.midel_animation);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        binding.imageA3.setAnimation(middleAnimation);




        BagerFragmentsAdapter adapter=new BagerFragmentsAdapter(this);
        adapter.addFragment(new ViewBaggerClass("Customer",new CustomerFragmentLogin()));
        adapter.addFragment(new ViewBaggerClass("service Provider",new ServiceProviderFragmentLogin()));


        binding.viewPager2.setAdapter(adapter);

        new TabLayoutMediator(binding.tapLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.arrayList.get(position).getTapName());



            }        }).attach();

        binding.viewPager2.setCurrentItem(1,false);







    }
}