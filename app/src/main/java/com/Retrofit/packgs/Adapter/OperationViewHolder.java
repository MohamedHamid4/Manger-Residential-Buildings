package com.Retrofit.packgs.Adapter;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.databinding.ItemOperationBinding;
import com.Retrofit.packgs.databinding.ItemPopBinding;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Employee;
import com.Retrofit.packgs.model.Operation;
import com.Retrofit.packgs.model.Pop;
import com.squareup.picasso.Picasso;

public class OperationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemOperationBinding binding;
        TaskCallBack callBack;
        Operation operation;

    public OperationViewHolder(@NonNull ItemOperationBinding binding, TaskCallBack callBack) {
        super(binding.getRoot());
        this.binding = binding;
        this.callBack = callBack;
        setOnClickListener();
    }
    public void setOperation (Operation operation){
        this.operation = operation;
        binding.tvName.setText("Name:"+operation.getActor().getName());
        binding.tvCategoryName.setText("Category:"+operation.getCategory_name());
        binding.tvAmount.setText("Amount:"+operation.getAmount());
        binding.tvDate.setText("Date:"+operation.getDate());
        binding.tvId.setText("Id Number:"+operation.getId());
    }

    private void setOnClickListener() {
        binding.deleteOpertian.setOnClickListener(this);
        binding.UpdateOpertian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete_pop) {
            callBack.onActionCallBack(ActionType.delete, getAdapterPosition(), operation.id);

        } else if (v.getId() == R.id.UpdattPop) {
            if (callBack != null) {
                callBack.onActionCallBack(ActionType.update, getAdapterPosition(), operation.id);
            }
        }
    }

}