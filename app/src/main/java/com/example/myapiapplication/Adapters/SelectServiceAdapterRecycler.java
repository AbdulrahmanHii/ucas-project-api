package com.example.myapiapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapiapplication.databinding.ItemSelectServiceCusBinding;
import com.example.myapiapplication.databinding.ItemSelectServiceCusBinding;

import java.util.ArrayList;


public class SelectServiceAdapterRecycler extends RecyclerView.Adapter<SelectServiceAdapterRecycler.SelectServiceViweHolder> {

    ArrayList<SelectServiceClass> arrayListService;
    SelectServesListener servesListener;

    public SelectServiceAdapterRecycler(ArrayList<SelectServiceClass> arrayListService, SelectServesListener servesListener) {
        this.arrayListService = arrayListService;
        this.servesListener = servesListener;
    }

    @NonNull
    @Override
    public SelectServiceViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectServiceCusBinding binding=ItemSelectServiceCusBinding.inflate(LayoutInflater.from(parent.getContext())
                ,parent,false);

        return new SelectServiceViweHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectServiceViweHolder holder, int position) {
        int pos =position;
        SelectServiceClass serviceClass = arrayListService.get(position);
        holder.nameService.setText(serviceClass.getNameService());
        holder.imageView.setImageDrawable(serviceClass.getIconService());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servesListener.selectedListener(pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListService.size();
    }


    public class SelectServiceViweHolder extends RecyclerView.ViewHolder{

        TextView nameService;
        ImageView imageView;

        public SelectServiceViweHolder(@NonNull ItemSelectServiceCusBinding binding) {
            super(binding.getRoot());

            nameService = binding.textJop;
            imageView = binding.imageIcon;



        }

    }
}
