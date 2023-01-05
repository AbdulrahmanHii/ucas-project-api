package com.example.myapiapplication.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class BagerFragmentsAdapter extends FragmentStateAdapter {

 public    ArrayList<ViewBaggerClass> arrayList =new ArrayList<>();

    public BagerFragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

   public void addFragment(ViewBaggerClass baggerClass){
        this.arrayList.add(baggerClass);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position).getFragment();
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
