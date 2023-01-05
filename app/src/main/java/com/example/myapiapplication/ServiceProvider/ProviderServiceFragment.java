package com.example.myapiapplication.ServiceProvider;

import static com.android.volley.Request.Method.POST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapiapplication.Adapters.AllServicesProviderAdapter;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Adapters.SelectServesListener;
import com.example.myapiapplication.Adapters.SelectServiceAdapterRecycler;
import com.example.myapiapplication.Adapters.SelectServiceClass;
import com.example.myapiapplication.databinding.FragmentProviderServiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderServiceFragment extends Fragment {
FragmentProviderServiceBinding binding;


    JsonObjectRequest request;
    RequestQueue queue;
    SelectServiceAdapterRecycler adapter;
    ArrayList<AllServicesProviderClass> servicesProviderArrayList = new ArrayList<>();
    SelectServiceClass serviceClass;
//    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5Nzc2N2JmYS1mZWVhLTQ0MTEtOWE0My0wYTliNzE4Y2YwZmEiLCJqdGkiOiI1YzAyOTMxZjFiMjE5YTAyMjE0YzJkNDkzOGQ0Yzg2NTM1ZGI3ZjIzZDY5NWNkYTFlNmVjMTE2OTI3Mjg0MGY3ZWI0ZDRjMWU5ZDZhZWRlNSIsImlhdCI6MTY3MTUyNzg1NCwibmJmIjoxNjcxNTI3ODU0LCJleHAiOjE3MDMwNjM4NTQsInN1YiI6IjgxIiwic2NvcGVzIjpbXX0.gdRA7WK52aDQ46vuIdW3IgRoOuEM-VqwQLVwn__3mfk3vO42n99tJOdYKPGr1fYoKG-2vSwX3t7k65XZRaGmVI6qWFl3XAm1LkoibnkgRBh-Wzf7_AKvPJONc08Ju58iTZfMppBgWNnNC51FyfGGXvhZ5Bj1cNQXPrzg8GoDip2fBVdjETus_ZEIep997-zw7AZv-Zpc6OEGJOuWoaKUohYMZM1BkLIBnwQ2zXJHA9eD18QnHp25AfJWtNegvW6MdX73E3JWKpRZiSZSyL6wat8ofg1NIzn_MbkFgs1_UEYttVslSjZFfYeEqwnKVESe_RpmO7knAr-eCboQyDtoagr10-XkFCxP2JM-jWMplAnSVn_O5Q1z52tUzy-yj8vti-oI6XqRp24MOHJ8BkVbH8IcWvXWjcujeWYboczV7qUX-QPlI4qo14xqvRK8N9aa8Tv8JnMGdr9Pa58YzR2gqxBziXE8HarA-cNJ91HDL0exAyeZJQr4PMyysU75cLi4_y9U3uRXoyVYMqqqNFNzVnchDojkQXWij7-gHkIN3Kcug7PXVRQQWqrh8vJODtPXLxh8C-MkrSfYhQYdnJM2KW-xG0_LJ_9cl6SbxJGDApEqpAnHO3cCan75oAhw059D93NV2NTBMpFJ7KWwkpyEWtX6MzukBdkf1b8gwMGkndk";



    String nameJop;
    int idJops;
    String icon;
    Context context1;
    String orderId;
    SharedPreferences sharedPreferences;
    String tokenSp;




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        context1 = context;
    }




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TOKEN = "tokenSP";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String tokenSP;
    private String mParam2;

    public ProviderServiceFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProviderServiceFragment newInstance(String token) {
        ProviderServiceFragment fragment = new ProviderServiceFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tokenSP = getArguments().getString(ARG_TOKEN);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProviderServiceBinding.inflate(inflater, container, false);
        queue = Volley.newRequestQueue(context1);
        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, 0);
        if (sharedPreferences!=null){
            tokenSp =   sharedPreferences.getString(Constant.SERVICE_PROVIDER_CONSTANT_TOKEN,"no");
        }

        allOrdersProvider();


        return binding.getRoot();
    }

    public void allOrdersProvider() {
        JSONObject jsonObject = new JSONObject();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(POST,
                "https://studentucas.awamr.com/api/home/deliver", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                AllServicesProviderAdapter allServicesProviderAdapter = null;
                try {

                    if (response.getBoolean("success")) {

                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Log.d("TAG", "onResponse: "+jsonArray.length());
                            String  image =  jsonArray.getJSONObject(i).getJSONObject("photo_order_home").getString("photo");
                            String nameCustomer =   jsonArray.getJSONObject(i).getJSONObject("user").getString("name");
                            String serviceType =   jsonArray.getJSONObject(i).getJSONObject("work").getString("name");
                            orderId =   jsonArray.getJSONObject(i).getString("id");
                            String date =   jsonArray.getJSONObject(i).getString("created_at");
//                            detailsOrder(orderId,tokenCustomer1);

                            binding.userNameProvider.setText(nameCustomer);

                            servicesProviderArrayList.add(new AllServicesProviderClass(image,date,serviceType,orderId,nameCustomer));
                            allServicesProviderAdapter = new AllServicesProviderAdapter(servicesProviderArrayList, new SelectServesListener() {
                                @Override
                                public void selectedListener(int position) {

                                }

                                @Override
                                public void selectedListenerProvider(int position) {


                                    SharedPreferences sp = context1.getSharedPreferences(Constant.saveAllWorkProvider,Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("nameCustomer",servicesProviderArrayList.get(position).getNameCustomer());
                                    editor.putString("date",servicesProviderArrayList.get(position).getDate());
                                    editor.putString("photoOrder", String.valueOf(servicesProviderArrayList.get(position).getPhoto()));
                                    editor.putString("serviceType",serviceType);
                                    editor.putString("orderId",orderId);

                                    Log.e("orderId", "selectedListenerProvider: "+orderId );
//                                    editor.putString("location",locationName);
                                    editor.apply();


                                    Intent intent = new Intent(context1, OrderDetailsProviderActivity.class);
                                    startActivity(intent);

                                }

                            });


                        }

                        binding.recyclerViewProvider.setAdapter(allServicesProviderAdapter);
                        binding.recyclerViewProvider.setLayoutManager(new LinearLayoutManager(getActivity()));

                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {


                Log.e("TAG", "getHeaders: "+tokenSp );

                Map<String, String> map = new HashMap<String, String>();
                map.put("Authorization", "Bearer "+sharedPreferences.getString(Constant.SERVICE_PROVIDER_CONSTANT_TOKEN,"0"));
            return map;
            }
        };


        queue.add(jsonObjectRequest);

    }
}