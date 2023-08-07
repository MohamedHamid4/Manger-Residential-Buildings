package com.Retrofit.packgs.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemEmployeeBinding;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.model.Employee;
import com.squareup.picasso.Picasso;


public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemEmployeeBinding binding;
    TaskCallBack callBack;
    Employee employee;

    public EmployeeViewHolder(@NonNull ItemEmployeeBinding binding, TaskCallBack callBack) {
        super(binding.getRoot());
        this.binding = binding;
        this.callBack = callBack;
        setOnClickListener();
    }

    public void setEmployee (Employee employee){
        this.employee = employee;
        binding.nameEmployeeTextView.setText(employee.name);
        binding.mobileEmployeeView.setText(employee.mobile);
        binding.nationalNumberEmployeeView.setText(employee.nationalNumber);
        Picasso.get().load(employee.imageUrl).into(binding.parsonEmp);
    }

    private void setOnClickListener(){
        binding.deleteEmp.setOnClickListener(this);
        binding.updatePop1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete_emp){
                callBack.onActionCallBack(ActionType.delete,getAdapterPosition(),employee.id);
            }else if (view.getId() == R.id.updatePop1){
                if (callBack != null){
                    callBack.onActionCallBack(ActionType.update,getAdapterPosition(),employee.id);
                }
            }
        }
    }
