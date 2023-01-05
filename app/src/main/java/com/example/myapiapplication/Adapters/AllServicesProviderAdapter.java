package com.example.myapiapplication.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapiapplication.ServiceProvider.AllServicesProviderClass;
import com.example.myapiapplication.databinding.ItemWorkProviderBinding;

import java.util.ArrayList;


public class AllServicesProviderAdapter extends RecyclerView.Adapter<AllServicesProviderAdapter.AllWorksProviderViweHolder> {

    ArrayList<AllServicesProviderClass> arrayListProvider;
    SelectServesListener servesListenerProvider;
    SendTokenInterface sendTokenInterface;

    public AllServicesProviderAdapter( ArrayList<AllServicesProviderClass> arrayListProvider, SelectServesListener servesListener) {
        this.arrayListProvider = arrayListProvider;
        this.servesListenerProvider = servesListener;
    }

    @NonNull
    @Override
    public AllWorksProviderViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkProviderBinding binding=ItemWorkProviderBinding.inflate(LayoutInflater.from(parent.getContext())
                ,parent,false);

        return new AllWorksProviderViweHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllWorksProviderViweHolder holder, int position) {
        int pos =position;
        AllServicesProviderClass providerClass = arrayListProvider.get(pos);
        holder.nameCustomer.setText(providerClass.getNameCustomer());
        holder.orderId.setText(providerClass.getOrderId());
        holder.date.setText(providerClass.getDate());
        String imageUrl =  arrayListProvider.get(pos).getPhoto();

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.photo);

        holder.serviceType.setText(providerClass.getServiceType());
        holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servesListenerProvider.selectedListenerProvider(pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListProvider.size();
    }


    public class AllWorksProviderViweHolder extends RecyclerView.ViewHolder{

        TextView date,serviceType,orderId,nameCustomer;
        Button detailsBtn;
        ImageView photo;


        public AllWorksProviderViweHolder(@NonNull ItemWorkProviderBinding binding) {
            super(binding.getRoot());

            photo = binding.imageViewWork;
            date = binding.dateWorkP;
            serviceType = binding.typeWorkTv3;
            orderId = binding.textOrderId;
            nameCustomer = binding.userNameProvider;
            detailsBtn = binding.btnDetails;

//            String imageUrl = photo.get(position);
//            ImageView imageView = holder.imageView;
//



        }

    }


}
