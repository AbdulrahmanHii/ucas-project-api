package com.example.myapiapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.BagerFragmentsAdapter;
import com.example.myapiapplication.Adapters.ViewBaggerClass;
import com.example.myapiapplication.Customer.CompletedCustomerFrg;
import com.example.myapiapplication.Customer.CustomerFragmentLogin;
import com.example.myapiapplication.Customer.PendingCusFragment;
import com.example.myapiapplication.Customer.UnCompleteCustomerFrg;
import com.example.myapiapplication.ServiceProvider.ServiceProviderFragmentLogin;
import com.example.myapiapplication.databinding.FragmentCustomerRegisterBinding;
import com.example.myapiapplication.databinding.FragmentOrdersCusBagerBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersCusFragmentBager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersCusFragmentBager extends Fragment {
    FragmentOrdersCusBagerBinding binding;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersCusFragmentBager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersCusFragmentBager.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersCusFragmentBager newInstance(String param1, String param2) {
        OrdersCusFragmentBager fragment = new OrdersCusFragmentBager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentOrdersCusBagerBinding.inflate(inflater, container, false);




        BagerFragmentsAdapter adapter=new BagerFragmentsAdapter(getActivity());
        adapter.addFragment(new ViewBaggerClass("Pending",new PendingCusFragment()));
        adapter.addFragment(new ViewBaggerClass("Underway",new UnCompleteCustomerFrg()));
        adapter.addFragment(new ViewBaggerClass("Completed",new CompletedCustomerFrg()));



        binding.recyclerOrderCus.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayoutOrderCus, binding.recyclerOrderCus, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.arrayList.get(position).getTapName());



            }
        }).attach();

        binding.recyclerOrderCus.setCurrentItem(1,false);

        return binding.getRoot();

    }
}