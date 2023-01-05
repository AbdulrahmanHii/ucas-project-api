package com.example.myapiapplication.ServiceProvider;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Adapters.SendTokenInterface;
import com.example.myapiapplication.HomeActivity;
import com.example.myapiapplication.ViewBagerProviderActivity;
import com.example.myapiapplication.databinding.FragmentServiceProviderRegisterBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceProviderFragmentRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceProviderFragmentRegister extends Fragment {
    FragmentServiceProviderRegisterBinding binding;


    JsonObjectRequest request;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    ServiceProviderDataClass dataSavedSP;
    ArrayList<String> jopsArray = new ArrayList<>();

    SendTokenInterface sendTokenInterface;
    String tokenRegister;


    String jopItemSelected;
    String token = "";
    String nameEt;
    String nameJop;
    int idJops;

    String email;
    String phone;
    String password;
    boolean saveBtn;

    boolean clickSpinner;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServiceProviderFragmentRegister() {
        // Required empty public constructor
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        sendTokenInterface = (SendTokenInterface) context;
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment serviceProviderFragmentRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceProviderFragmentRegister newInstance(String param1, String param2) {
        ServiceProviderFragmentRegister fragment = new ServiceProviderFragmentRegister();
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


        binding = FragmentServiceProviderRegisterBinding.inflate(inflater, container, false);

        queue = Volley.newRequestQueue(getActivity());
        getFromSp();


        ReadAllData();


        binding.signUpBtnSFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameEt = binding.fullNameSPFrg.getText().toString();
                email = binding.emailSPFrg.getText().toString();
                phone = binding.phoneSPFrg.getText().toString();
                password = binding.passwordEtSPFrg.getText().toString();
                saveBtn = binding.rememberMeRBSFrg.isChecked();


                if (!nameEt.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!phone.isEmpty()) {
                            if (!password.isEmpty()) {
                                if (clickSpinner) {

                                    if (saveBtn) {

                                        sendDataToSever(nameEt, email, phone, password, idJops);

                                        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, 0);
                                        editor = sharedPreferences.edit();
                                        editor.putBoolean(Constant.HAVE_TOKEN, true);

                                        dataSavedSP = new ServiceProviderDataClass(nameEt, email, phone, password, jopItemSelected);
                                        dataSavedSP.setChickState(true);

                                        editor.putString(Constant.saveNameService, gson.toJson(dataSavedSP));
                                        editor.apply();


                                    } else sendDataToSever(nameEt, email, phone, password, idJops);
                                    dataSavedSP.setChickState(false);
                                    editor.putBoolean(Constant.HAVE_TOKEN, false);
                                    editor.apply();
                                } else
                                    Toast.makeText(getActivity(), " please enter your jop", Toast.LENGTH_SHORT).show();
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

        binding.signInTvSFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();


    }


    void sendDataToSever(String fullNameS, String emailS, String phoneS, String passwordS, int jopsId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", fullNameS);
            jsonObject.put("email", emailS);
            jsonObject.put("phone", phoneS);
            jsonObject.put("password", passwordS);
            jsonObject.put("work_id", jopsId);


            request = new JsonObjectRequest(POST,
                    "https://studentucas.awamr.com/api/auth/register/delivery", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        if (response.getBoolean("success")) {
                            JSONObject jsonObjectSP = response.getJSONObject("data");

                            tokenRegister = jsonObjectSP.getString("token");

                            Log.e("token", "token__: " + tokenRegister);

//                            sendTokenInterface.getTokenServicesProvider(tokenvtRegister);
                            editor.putString(Constant.SERVICE_PROVIDER_CONSTANT_TOKEN,tokenRegister);
                            editor.apply();


                            Intent intent = new Intent(getActivity(), ViewBagerProviderActivity.class);
                            startActivity(intent);

                        }

//                            Intent intent=new Intent(getActivity(), CusSelectServiceFragment.class);
//                            startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity(), "error" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
//

        } catch (JSONException e) {
            e.printStackTrace();

        }
        queue.add(request);

    }


    void ReadAllData() {
        StringRequest requestGetData = new StringRequest(GET, "https://studentucas.awamr.com/api/all/works", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jArray = jsonObject.getJSONArray("data");
//                    binding.progress.setVisibility(View.GONE);


                    JSONObject jObject;
                    for (int i = 0; i < jArray.length(); i++) {
                        jObject = jArray.getJSONObject(i);
                        nameJop = jObject.getString("name");
                        idJops = jObject.getInt("id");


                        jopsArray.add(nameJop);
                        Log.e("myData", "myData" + nameJop + idJops + jopsArray);


                    }
///Spinner--------
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, jopsArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    binding.selectServiceSpinner.setAdapter(adapter);
                    //create Spinner onItemSelectedListener
                    binding.selectServiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            Log.v("item", (String) adapterView.getItemAtPosition(i));
                            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.RED);
                            jopItemSelected = (String) adapterView.getItemAtPosition(i);

//
                            if (adapterView.getSelectedItem() != null)
                                clickSpinner = true;
                            else clickSpinner = false;

//                                if (adapterView.performItemClick(view,i,idJops)){
//                                    clickSpinner=true;
//                                }else      clickSpinner=false;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });//create Spinner onItemSelectedListener end

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(requestGetData);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    void getFromSp() {
        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, MODE_PRIVATE);
        String registerString = sharedPreferences.getString(Constant.saveNameService, "1");

        if (!registerString.isEmpty()) {
            dataSavedSP = gson.fromJson(registerString, ServiceProviderDataClass.class);

            nameEt = dataSavedSP.getFullNameS();
            email = dataSavedSP.getEmailS();
            phone = dataSavedSP.getPhoneS();
            password = dataSavedSP.getPasswordS();
            jopItemSelected = dataSavedSP.getJopSelectedS();
            saveBtn = dataSavedSP.getChickState();

            binding.fullNameSPFrg.setText(nameEt);
            binding.emailSPFrg.setText(email);
            binding.phoneSPFrg.setText(phone);
            binding.passwordEtSPFrg.setText(password);
            if (binding.rememberMeRBSFrg.isChecked()) {
                binding.rememberMeRBSFrg.setChecked(true);
            } else binding.rememberMeRBSFrg.setChecked(false);


        }

    }

}