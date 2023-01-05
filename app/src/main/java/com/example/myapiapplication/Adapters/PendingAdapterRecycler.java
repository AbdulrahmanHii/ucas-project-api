package com.example.myapiapplication.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapiapplication.databinding.ItemAdapterCustomerBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PendingAdapterRecycler extends RecyclerView.Adapter<PendingAdapterRecycler.PendingViweHolder> {

    ArrayList<PendingClass> arrayListPending;
//    SelectServesListener servesListener;

    public PendingAdapterRecycler(ArrayList<PendingClass> arrayListPendingS) {
        this.arrayListPending = arrayListPendingS;

    }

    @NonNull
    @Override
    public PendingViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdapterCustomerBinding binding=ItemAdapterCustomerBinding.inflate(LayoutInflater.from(parent.getContext())
                ,parent,false);

        return new PendingViweHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViweHolder holder, int position) {
        int pos = position;
        PendingClass pendingClass = arrayListPending.get(position);
        holder.OrderNum.setText(pendingClass.getOrderNum()+"");
        holder.serviceType.setText(pendingClass.getServiceType()+"");
        holder.dateTv.setText(pendingClass.getOrderDateShow()+"");
//        holder.dateTv.setText();



    }
    @Override
    public int getItemCount() {
        return arrayListPending.size();
    }


    public class PendingViweHolder extends RecyclerView.ViewHolder{

        TextView OrderNum,serviceType,dateTv;

        public PendingViweHolder(@NonNull ItemAdapterCustomerBinding binding) {
            super(binding.getRoot());

            OrderNum = binding.textViewOrderNum;
            serviceType = binding.typeJopTv;
            dateTv = binding.dateTv;



        }

    }
}
