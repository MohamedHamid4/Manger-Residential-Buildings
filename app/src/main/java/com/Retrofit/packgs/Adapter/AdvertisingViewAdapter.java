package com.Retrofit.packgs.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemAdvertisingBinding;
import com.Retrofit.packgs.databinding.ItemEmployeeBinding;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.AdMangment;
import com.Retrofit.packgs.model.Employee;

import java.util.List;

public class AdvertisingViewAdapter extends RecyclerView.Adapter<AdMangmentViewHolder> {
    List<AdMangment> adMangments;
    TaskCallBack callBack;

    public AdvertisingViewAdapter(List<AdMangment> adMangments) {
        this.adMangments = adMangments;
    }

    @NonNull
    @Override
    public AdMangmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdvertisingBinding binding = ItemAdvertisingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdMangmentViewHolder(binding,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull AdMangmentViewHolder holder, int position) {
        holder.setEmployee(adMangments.get(position));

    }

    @Override
    public int getItemCount() {
        return adMangments.size();
    }

    public void setCallBack(TaskCallBack callBack) {
        this.callBack = callBack;
    }

}