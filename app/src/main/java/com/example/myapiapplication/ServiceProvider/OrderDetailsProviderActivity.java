package com.example.myapiapplication.ServiceProvider;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Customer.CusSelectServiceFragment;
import com.example.myapiapplication.ViewBagerProviderActivity;
import com.example.myapiapplication.databinding.ActivityOrderDetailsProviderBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailsProviderActivity extends AppCompatActivity {
ActivityOrderDetailsProviderBinding binding;

    JsonObjectRequest request;
    RequestQueue queue;
    Context context=this;

    String orderId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityOrderDetailsProviderBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        sharedPreferences =context.getSharedPreferences(Constant.fileName, 0);


        queue = Volley.newRequestQueue(context);




        SharedPreferences sp= getSharedPreferences(Constant.saveAllWorkProvider, MODE_PRIVATE);
        String nameProvider= sp.getString("nameCustomer","");
        String date= sp.getString("date","");
        String photoOrder= sp.getString("photoOrder","No Image");
        String serviceType= sp.getString("serviceType","");
        String phone= sp.getString("phone","0594445556");
         orderId= sp.getString("orderId","1");

        Log.d("dataSp", "onCreate: "+nameProvider+"---"+date+"---"+
                phone+"---"+serviceType+"---"+orderId);


//        String token= sp.getString("phone","0594445556");



        binding.userNameProvider.setText(nameProvider);
        binding.dateTv.setText(date);
        binding.nameWorkTv.setText(serviceType);
        binding.phoneTv.setText(phone);
        binding.locationTv.setText("GAZA");
        Glide.with(context)
                .load(photoOrder)
                .into(binding.imageViewWork);


        binding.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, ViewBagerProviderActivity.class);
//                startActivity(intent);

                sendDataToSever( orderId);
            }
        });
        binding.imageViewBackSmithA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewBagerProviderActivity.class);
                startActivity(intent);

            }
        });


    }
    void sendDataToSever(String orderId) {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("order_id", orderId);


            request = new JsonObjectRequest(POST,
                    "https://studentucas.awamr.com/api/create/Offer", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();

                        if (response.getBoolean("success")){
                            Intent intent=new Intent(context, ViewBagerProviderActivity.class);
                            startActivity(intent);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            })
            {
                String  tokenP;
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (sharedPreferences!=null){
                          tokenP=   sharedPreferences.getString(Constant.SERVICE_PROVIDER_CONSTANT_TOKEN,"no");
                    }

                    Map<String,String> map = new HashMap<String,String>();
                    map.put("Authorization","Bearer "+tokenP);
                    return map;

                }};

        } catch (JSONException e) {
            e.printStackTrace();

        }
        queue.add(request);

    }





}