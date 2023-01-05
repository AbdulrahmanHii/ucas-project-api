package com.example.myapiapplication.Customer;

import static com.android.volley.Request.Method.POST;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Customer.LocationActivity;
import com.example.myapiapplication.R;
import com.example.myapiapplication.databinding.ActivitySmithBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmithActivity extends AppCompatActivity {
ActivitySmithBinding binding;

    JsonObjectRequest request;
    RequestQueue queue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context=this;




    String token;
    String email;
    String password;
    Boolean imageChecker;
    Intent in = null;
    List<Uri> imageUris = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySmithBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getApplication().getSharedPreferences(Constant.fileName, MODE_PRIVATE);

        queue = Volley.newRequestQueue(context);

        Intent intent = getIntent();
        int idWork = intent.getIntExtra("id_work", 1);



        ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {


                            if (result.getResultCode() == RESULT_OK) {
                                 imageUris = new ArrayList<>();
                                 in = result.getData();
                                // Get the selected images
                                if (in.getClipData() != null) {
                                    // Multiple images were selected
                                    int count = in.getClipData().getItemCount();
                                    for (int i = 0; i < count; i++) {
                                        Uri imageUri = in.getClipData().getItemAt(i).getUri();
                                        // Do something with the image
                                        imageUris.add(imageUri);
//                                        binding.imageView.setImageURI(imageUri);
                                        Toast.makeText(context, "you Selected "+imageUris.size()+"photo", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                // Do something with the image
                                Uri imageUri = in.getData();
                                imageUris.add(imageUri);
                                Toast.makeText(context, "selected image please!!", Toast.LENGTH_SHORT).show();
                            }


                    }
                });
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                activityResultLauncher.launch(intent);


                            }
                        });

                        binding.btnA2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String detailsEt=binding.textViewSmithA.getText().toString();

                                Intent intent1 = new Intent(context, LocationActivity.class);
                                intent1.putParcelableArrayListExtra("image_urls", (ArrayList<? extends Parcelable>) imageUris);
                                intent1.putExtra("details",detailsEt);
                                startActivity(intent1);
                            }
                        });


                    }


        }



