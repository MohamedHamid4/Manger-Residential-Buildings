package com.Retrofit.packgs.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemEmployeeBinding;
import com.Retrofit.packgs.databinding.ItemOperationBinding;
import com.Retrofit.packgs.databinding.ItemPopBinding;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Operation;

import java.util.ArrayList;

public class OperationAdapter extends RecyclerView.Adapter<OperationViewHolder> {

    ArrayList<Operation> operations;
    TaskCallBack callBack;

    public OperationAdapter(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOperationBinding binding = ItemOperationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OperationViewHolder(binding,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder holder, int position) {
        holder.setOperation(operations.get(position));
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    public void setCallBack(TaskCallBack callBack) {
        this.callBack = callBack;
    }

}