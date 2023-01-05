package com.example.myapiapplication.Customer;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.Method.POST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;


import com.example.myapiapplication.Adapters.SendTokenInterface;
import com.example.myapiapplication.HomeActivity;
import com.example.myapiapplication.ViewBagerServiceActivityCus;
import com.example.myapiapplication.databinding.FragmentCustomerRegisterBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragmentRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragmentRegister extends Fragment {
    FragmentCustomerRegisterBinding binding;



    JsonObjectRequest request;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    CustomerDataClass dataSavedCus;
    SendTokenInterface sendTokenInterface;





    String token;
    String name;
    String email;
    String phone;
    String password;
    boolean saveBtn;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        sendTokenInterface = (SendTokenInterface) context;}
//


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerFragmentRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerFragmentRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerFragmentRegister newInstance(String param1, String param2) {
        CustomerFragmentRegister fragment = new CustomerFragmentRegister();
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
        binding = FragmentCustomerRegisterBinding.inflate(inflater, container, false);
        queue = Volley.newRequestQueue(getActivity());
        getFromSp();




        binding.signUpBtnCusFrg.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                name     = binding.fullNameCusPFrg.getText().toString();
                email    = binding.emailCusPFrg.getText().toString();
                phone    = binding.phoneCusPFrg.getText().toString();
                password = binding.passwordEtCusPFrg.getText().toString();
                saveBtn  = binding.rememberMeRBCusFrg.isChecked();


                if (!name.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!phone.isEmpty()) {
                            if (!password.isEmpty()) {


                                    if (saveBtn) {
                                        sendDataToSever(name, email, phone, password);
                                        Log.e("token", "onClick: "+token );

                                        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, 0);
                                        editor = sharedPreferences.edit();
                                        editor.putBoolean(Constant.HAVE_TOKEN,true);

                                        dataSavedCus = new CustomerDataClass(name, email, phone, password);
                                        dataSavedCus.setChickStateCus(true);

                                        editor.putString(Constant.saveNameCustomer, gson.toJson(dataSavedCus));
                                        editor.apply();



                                    }else sendDataToSever(name, email, phone, password);
                                dataSavedCus.setChickStateCus(false);
                                editor.putBoolean(Constant.HAVE_TOKEN,false);
                                editor.apply();


                            } else
                                Toast.makeText(getActivity(), " please enter your password", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getActivity(), " please enter your phone Number", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getActivity(), " please enter your email", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), " please enter your full name", Toast.LENGTH_SHORT).show();



            }
        });

        binding.signInTvCusFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });




        return binding.getRoot();
    }

    void sendDataToSever(String fullNameC, String emailC, String phoneC, String passwordC) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", fullNameC);
            jsonObject.put("email", emailC);
            jsonObject.put("phone",phoneC );
            jsonObject.put("password", passwordC);



            request = new JsonObjectRequest(POST,
                    "https://studentucas.awamr.com/api/auth/register/user", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        if (response.getBoolean("success")){
                            JSONObject jsonObjectSP= response.getJSONObject("data");

                            String tokenR_Cus =    jsonObjectSP.getString("token");

                            Log.e("token", "tokennnnn__: "+ tokenR_Cus);

//                            sendTokenInterface.getTokenServicesProvider(tokenR_Cus);
                            //send token to View Bager Service Activity Cus
                            editor.putString(Constant.CUSTOMER_CONSTANT_TOKEN,tokenR_Cus);
                            editor.apply();
                                
                                    Intent intent=new Intent(getActivity(), ViewBagerServiceActivityCus.class);
                                    startActivity(intent);

                                }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(), "error:"+error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
//            {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//
//                    Map<String,String> map = new HashMap<String,String>();
//                    map.put("Authorization","Bearer "+token);
//
//                    return map;
//
//                }};

        } catch (JSONException e) {
            e.printStackTrace();

        }
        queue.add(request);

    }




    @Override
    public void onDestroy() {
        super.onDestroy();

//

    }
    void getFromSp(){

        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, MODE_PRIVATE);
        String registerString = sharedPreferences.getString(Constant.saveNameCustomer, "1");

        if (!registerString.isEmpty()) {
            CustomerDataClass dataClass  = gson.fromJson(registerString, CustomerDataClass.class);

            name =dataClass.getFullNameCus();
            email=dataClass.getEmailCus();
            phone=dataClass.getPhoneCus();
            password=dataClass.getPasswordCus();
            saveBtn=dataClass.getChickStateCus();

            binding.fullNameCusPFrg.setText(name);
            binding.emailCusPFrg.setText(email);
            binding.phoneCusPFrg.setText(phone);
            binding.passwordEtCusPFrg.setText(password);
            if (binding.rememberMeRBCusFrg.isChecked()){
                binding.rememberMeRBCusFrg.setChecked(true);
            }else binding.rememberMeRBCusFrg.setChecked(false);


        }

    }

}