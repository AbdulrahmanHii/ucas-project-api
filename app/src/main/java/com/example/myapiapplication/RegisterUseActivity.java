package com.example.myapiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapiapplication.Adapters.BagerFragmentsAdapter;
import com.example.myapiapplication.Adapters.ViewBaggerClass;
import com.example.myapiapplication.Customer.CustomerFragmentRegister;
import com.example.myapiapplication.ServiceProvider.ServiceProviderFragmentRegister;
import com.example.myapiapplication.databinding.ActivityRegesterUseBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RegisterUseActivity extends AppCompatActivity {

    ActivityRegesterUseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegesterUseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        CustomerFragmentRegister customerFragmentRegister=new CustomerFragmentRegister();
//        FragmentManager fm=getSupportFragmentManager();
//        FragmentTransaction ft=fm.beginTransaction();
//        ft.replace(R.id.view_pager2Register,customerFragmentRegister).addToBackStack("f");
//        ft.commit();
//
//        serviceProviderFragmentRegister spFRegister =new serviceProviderFragmentRegister();
//        FragmentManager fm2=getSupportFragmentManager();
//        FragmentTransaction ft2=fm.beginTransaction();
//        ft.replace(R.id.view_pager2Register,spFRegister).addToBackStack("f");
//        ft.commit();


//        getSupportFragmentManager().beginTransaction().add(R.id.view_pager2Register,new CustomerFragmentRegister()).commit();

//        binding.tapLayout.setupWithViewPager(binding.viewPager2Register);

        BagerFragmentsAdapter adapter=new BagerFragmentsAdapter(this);
        adapter.addFragment(new ViewBaggerClass("Customer",new CustomerFragmentRegister()));
        adapter.addFragment(new ViewBaggerClass("serviceProvider",new ServiceProviderFragmentRegister()));

        binding.viewPager2Register.setAdapter(adapter);

        new TabLayoutMediator(binding.tapLayout, binding.viewPager2Register, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.arrayList.get(position).getTapName());



            }
        }).attach();

        binding.viewPager2Register.setCurrentItem(1,false);







    }
}