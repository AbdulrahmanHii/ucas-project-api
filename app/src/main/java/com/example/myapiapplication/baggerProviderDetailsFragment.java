package com.example.myapiapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapiapplication.Adapters.BagerFragmentsAdapter;
import com.example.myapiapplication.Adapters.ViewBaggerClass;
import com.example.myapiapplication.ServiceProvider.CompletedProviderFragment;
import com.example.myapiapplication.ServiceProvider.UnCompletedProviderFragment;
import com.example.myapiapplication.databinding.FragmentBaggerProviderDetailsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link baggerProviderDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class baggerProviderDetailsFragment extends Fragment {
    FragmentBaggerProviderDetailsBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public baggerProviderDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment baggerProviderDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static baggerProviderDetailsFragment newInstance(String param1, String param2) {
        baggerProviderDetailsFragment fragment = new baggerProviderDetailsFragment();
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

        binding = FragmentBaggerProviderDetailsBinding.inflate(inflater, container, false);




        BagerFragmentsAdapter adapter=new BagerFragmentsAdapter(getActivity());
        adapter.addFragment(new ViewBaggerClass("Un Completed",new UnCompletedProviderFragment()));
        adapter.addFragment(new ViewBaggerClass("Completed",new CompletedProviderFragment()));



        binding.recyclerProviderA.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout2ProviderA, binding.recyclerProviderA, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.arrayList.get(position).getTapName());



            }
        }).attach();

        binding.recyclerProviderA.setCurrentItem(1,false);

        return binding.getRoot();

    }

}