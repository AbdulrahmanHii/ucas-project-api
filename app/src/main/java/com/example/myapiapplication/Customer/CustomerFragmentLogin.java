package com.example.myapiapplication.Customer;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.Method.POST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import com.example.myapiapplication.RegisterUseActivity;
import com.example.myapiapplication.ViewBagerServiceActivityCus;
import com.example.myapiapplication.databinding.LoginFragmentCustomerBinding;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragmentLogin extends Fragment {
    LoginFragmentCustomerBinding binding;

    MediaPlayer mediaPlayer;


    JsonObjectRequest request;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    SendTokenInterface sendTokenInterface;




    String token;
    String email;
    String password;
    Boolean saveBtn;



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        sendTokenInterface = (SendTokenInterface) context;
//    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerFragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerFragmentLogin newInstance(String param1, String param2) {
        CustomerFragmentLogin fragment = new CustomerFragmentLogin();
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
        binding = LoginFragmentCustomerBinding.inflate(inflater, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, MODE_PRIVATE);
        if (sharedPreferences!=null){
            boolean saveMeBtn= sharedPreferences.getBoolean("Btn_Remember_Me",true);
            if (saveMeBtn){
                String  email=sharedPreferences.getString("email_loginCus","zeez@gmail.com");
                String  password=sharedPreferences.getString("password_loginCus","100100");
                binding.passwordEtCusPFrgFrg.setText(password);
                binding.emailEtCusFrg.setText(email);
                binding.rememberMeRBCusFrgFrg.setChecked(true);

            }else  binding.rememberMeRBCusFrgFrg.setChecked(false);
        }


        queue = Volley.newRequestQueue(getActivity());




        binding.loginBtnCusFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email    = binding.emailEtCusFrg.getText().toString();
                password = binding.passwordEtCusPFrgFrg.getText().toString();
                saveBtn  = binding.rememberMeRBCusFrgFrg.isChecked();




                    if (!email.isEmpty()) {
                            if (!password.isEmpty()) {
                                if (saveBtn) {

                                    sendDataToSever(email,password);

                                    sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, 0);
                                    editor = sharedPreferences.edit();
                                    editor.putString("email_loginCus",email);
                                    editor.putString("password_loginCus",password);
                                    editor.putBoolean("Btn_Remember_Me",saveBtn);
                                    editor.apply();

//                                    Intent intent=new Intent(getActivity(),CusSelectServiceFragment.class);
//                                    startActivity(intent);


                                }else sendDataToSever( email, password);
                                editor.putBoolean("Btn_Remember_Me",saveBtn);


                            } else
                                Toast.makeText(getActivity(), " please enter your password", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getActivity(), " please enter your email", Toast.LENGTH_SHORT).show();


            }
        });

        binding.signUpTvCusFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), RegisterUseActivity.class);
                startActivity(intent);
            }
        });




        return binding.getRoot();
    }

    void sendDataToSever(String emailC, String passwordC) {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("email", emailC);
            jsonObject.put("password", passwordC);

            request = new JsonObjectRequest(POST,
                    "https://studentucas.awamr.com/api/auth/login/user", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        if (response.getBoolean("success")){
                            JSONObject jsonObjectSP= response.getJSONObject("data");

                            String tokenL_Cus =    jsonObjectSP.getString("token");

                            Log.e("token", "tokenn__: "+ tokenL_Cus);
                            editor.putString(Constant.CUSTOMER_CONSTANT_TOKEN,tokenL_Cus);
                            editor.apply();
//                            sendTokenInterface.getTokenCustomer(tokenL_Cus);

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

                    Toast.makeText(getActivity(), "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

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

        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, MODE_PRIVATE);

        String email_loginCus= sharedPreferences.getString("email_loginCus","");
        String password_loginCus= sharedPreferences.getString("password_loginCus","");
        Boolean btnRememberMe = sharedPreferences.getBoolean("Btn_Remember_Me",false);

        binding.passwordEtCusPFrgFrg.setText(email_loginCus);
        binding.emailEtCusFrg.setText(password_loginCus);

            if (btnRememberMe){
                binding.rememberMeRBCusFrgFrg.setChecked(true);
            }else binding.rememberMeRBCusFrgFrg.setChecked(false);




        }

    }




