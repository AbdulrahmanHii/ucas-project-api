package com.example.myapiapplication.Customer;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.android.volley.Request.Method.POST;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.R;
import com.example.myapiapplication.databinding.ActivityLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityLocationBinding binding;

    JsonObjectRequest request;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context = this;
    private GoogleMap mMap;
    Location crLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_COOD = 101;


    String createODate;
    String orderIdd;
    String workName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        sharedPreferences = context.getSharedPreferences(Constant.fileName, 0);



//TODO: get  List<Uri> imageUris from smith activity :
        List<Uri> imageUris = getIntent().getParcelableArrayListExtra("image_urls");
        String details = getIntent().getStringExtra("details");

//Convert list Uri to file:
        List<File> imagesFile = new ArrayList<>();

        for (Uri imageUri : imageUris) {
            File image = uriToFile(imageUri);
            imagesFile.add(image);
        }

//TODO: call func  getCurrentLocation() to get lat and long :
        getCurrentLocation();


        queue = Volley.newRequestQueue(context);

        binding.btnA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      //TODO: send data to server
                String phone = binding.phoneSPFrg.getText().toString();
                sendDataToSever(phone, details, imagesFile);

            }


        });


    }

    void sendDataToSever(String phone, String details_address, List<File> files) {
        String orderId;
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("lat", crLocation.getLongitude());
            jsonObject.put("long", crLocation.getLongitude());
            jsonObject.put("phone", phone);
            jsonObject.put("details_address", details_address);
            jsonObject.put("photos[]", files);

            request = new JsonObjectRequest(POST,
                    "https://studentucas.awamr.com/api/create/order", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        JSONObject jsonObject1 = null;
                        if (response.getBoolean("success")) {
                            Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject1 = jsonArray.getJSONObject(i);
                                workName = jsonObject1.getJSONObject("work").getString("name");

                                JSONObject jsonObject2;
                                JSONArray jsonArray2 = jsonObject1.getJSONArray("photo_order");
                                for (int j = 0; j < jsonArray2.length(); j++) {

                                    jsonObject2 = jsonArray2.getJSONObject(j);
                                    orderIdd = jsonObject2.getString("order_id");
                                    createODate = jsonObject2.getString("created_at");

                                    sharedPreferences = getSharedPreferences(Constant.fileName, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("ORDER_ID", orderIdd);
                                    editor.putString("ORDER_DATE", createODate);
                                    editor.putString("ORDER_WORK_NAME", workName);
                                    editor.apply();

                                    Intent intent = new Intent(context, OrderDoneActivity.class);
                                    startActivity(intent);
                                }

                            }


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }) {
                String tokenCus;

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    if (sharedPreferences != null) {
                        tokenCus = sharedPreferences.getString(Constant.CUSTOMER_CONSTANT_TOKEN, "no");
                    }
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Authorization", "Bearer " + tokenCus);
                    return map;
                }
            };

        } catch (JSONException e) {
            e.printStackTrace();

        }
        queue.add(request);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(crLocation.getLatitude(), crLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COOD);
            return;
        }
        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    crLocation = location;
//
                    com.google.android.gms.maps.SupportMapFragment mapFragment = (com.google.android.gms.maps.SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.mapFrg);

                    mapFragment.getMapAsync((com.google.android.gms.maps.OnMapReadyCallback) LocationActivity.this);

                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (REQUEST_COOD) {
            case REQUEST_COOD:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
        }
    }

    private File uriToFile(Uri uri) {
        // Get the path of the file from the uri
        String path = getRealPathFromUri(uri);

        // Create a file object from the path
        return new File(path);
    }

    private String getRealPathFromUri(Uri uri) {
        // Get the file path from the uri
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            path = cursor.getString(idx);
            cursor.close();
        }
        return path;
    }

}