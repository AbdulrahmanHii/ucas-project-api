package com.example.myapiapplication.Customer;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapiapplication.Adapters.Constant;
import com.example.myapiapplication.Adapters.SelectServesListener;
import com.example.myapiapplication.Adapters.SelectServiceAdapterRecycler;
import com.example.myapiapplication.Adapters.SelectServiceClass;
import com.example.myapiapplication.R;
import com.example.myapiapplication.databinding.FragmentCusSelectServiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CusSelectServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CusSelectServiceFragment extends Fragment  {
    FragmentCusSelectServiceBinding binding;


    JsonObjectRequest request;
    RequestQueue queue;
    SelectServiceAdapterRecycler adapter;
    ArrayList<SelectServiceClass> arrayList = new ArrayList<>();
    SelectServiceClass serviceClass;
    SharedPreferences sharedPreferences;




    String nameJop;
    int idJops;
    String icon;
    Context context1;





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        context1 = context;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TOKEN  = "tokenCustomer";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String tokenCustomer;
//    private String mParam2;

    public CusSelectServiceFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CusSelectServiceFragment newInstance(String tokenCustomer) {
        CusSelectServiceFragment fragment = new CusSelectServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOKEN, tokenCustomer);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tokenCustomer = getArguments().getString(ARG_TOKEN);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferences = getActivity().getSharedPreferences(Constant.fileName, MODE_PRIVATE);

        binding = FragmentCusSelectServiceBinding.inflate(inflater, container, false);


        queue = Volley.newRequestQueue(context1);

        ReadAllData();

        return binding.getRoot();
    }

    void ReadAllData() {
        StringRequest requestGetData = new StringRequest(GET, "https://studentucas.awamr.com/api/all/works", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jArray = jsonObject.getJSONArray("data");
//                    binding.progress.setVisibility(View.GONE);
                    Toast.makeText(context1, ""+jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                    JSONObject jObject;
                    for (int i = 0; i < jArray.length(); i++) {
                        jObject = jArray.getJSONObject(i);

                        nameJop = jObject.getString("name");
                        idJops = jObject.getInt("id");
//                         icon = (Drawable) jObject.getString("icon");

                        Log.e("myData", "myData" + nameJop + idJops);

                        arrayList.add(new SelectServiceClass(nameJop, idJops, getResources().getDrawable(R.drawable.water1)));
                        adapter = new SelectServiceAdapterRecycler(arrayList, new SelectServesListener() {
                            @Override
                            public void selectedListener(int position) {
                              int id =  arrayList.get(position).getIdService();
                              Intent intent=new Intent(context1, SmithActivity.class);
                              intent.putExtra("id_Word",id);
                              startActivity(intent);

                            }

                            @Override
                            public void selectedListenerProvider(int position) {

                            }
                        });

                        binding.recyclerServes.setAdapter(adapter);
                        binding.recyclerServes.setLayoutManager(new GridLayoutManager(getActivity(), 3));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context1, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(requestGetData);
    }


}