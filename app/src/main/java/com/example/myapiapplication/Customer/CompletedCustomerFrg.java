package com.example.myapiapplication.Customer;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Adapters.PendingAdapterRecycler;
import com.example.myapiapplication.Adapters.PendingClass;
import com.example.myapiapplication.Adapters.SelectServiceClass;
import com.example.myapiapplication.databinding.FragmentPendingCusBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedCustomerFrg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedCustomerFrg extends Fragment {
    FragmentPendingCusBinding binding;


    JsonObjectRequest request;
    RequestQueue queue;
    PendingAdapterRecycler adapter;
    ArrayList<PendingClass> arrayListPending = new ArrayList<>();
    SelectServiceClass serviceClass;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    SharedPreferences sharedPreferences;

    String OrderNum;

    String serviceType;
    Context context1;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        context1 = context;
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompletedCustomerFrg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pendingProviderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletedCustomerFrg newInstance(String param1, String param2) {
        CompletedCustomerFrg fragment = new CompletedCustomerFrg();
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
        binding = FragmentPendingCusBinding.inflate(inflater, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, 0);


        queue = Volley.newRequestQueue(context1);

        ReadAllData();
        sharedPreferences=getActivity().getSharedPreferences(Constant.fileName,MODE_PRIVATE);
     String orderId    =  sharedPreferences.getString("ORDER_ID","1");
     String orderDate    =  sharedPreferences.getString("ORDER_DATE","1");
     String orderWorkName    =  sharedPreferences.getString("ORDER_WORK_NAME","1");

//  TODO:  arrayListPending.add(new PendingClass(OrderNum,serviceType,date));

        arrayListPending.add(new PendingClass(orderId,orderDate,orderWorkName));
        adapter = new PendingAdapterRecycler(arrayListPending);

        binding.recyclerViewPending.setAdapter(adapter);
        binding.recyclerViewPending.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL,false));



        return binding.getRoot();
    }

    void ReadAllData() {

        StringRequest requestGetData = new StringRequest(GET, "https://studentucas.awamr.com/api/order/complete/user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jArray = jsonObject.getJSONArray("data");
//                    binding.progress.setVisibility(View.GONE);


                    JSONObject jObject;
                    for (int i = 0; i < jArray.length(); i++) {
                        jObject = jArray.getJSONObject(i);

                        OrderNum = jObject.getString("code");
                        serviceType= jObject.getString("name");

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {
            String tokenCus;
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                if (sharedPreferences!=null){
                    tokenCus =   sharedPreferences.getString(Constant.CUSTOMER_CONSTANT_TOKEN,"no");
                }
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("Authorization","Bearer "+tokenCus);
                    return map;

                }};


        queue.add(requestGetData);
    }


}