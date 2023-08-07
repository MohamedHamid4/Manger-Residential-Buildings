package com.Retrofit.packgs.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemPopBinding;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Pop;

import java.util.List;

public class PopViewAdapter extends RecyclerView.Adapter<PopViewHolder> {
    List <Pop> users;
    TaskCallBack callBack;


    public PopViewAdapter(List<Pop> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public PopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopBinding binding = ItemPopBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PopViewHolder(binding,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull PopViewHolder holder, int position) {
        holder.setUserData(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setCallBack(TaskCallBack callBack) {
        this.callBack = callBack;
    }
}
