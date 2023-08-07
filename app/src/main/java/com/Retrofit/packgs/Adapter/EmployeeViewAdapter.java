package com.Retrofit.packgs.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemEmployeeBinding;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Employee;

import java.util.List;

public class EmployeeViewAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    List<Employee> employees;
    TaskCallBack callBack;

    public EmployeeViewAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EmployeeViewHolder(binding,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.setEmployee(employees.get(position));

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setCallBack(TaskCallBack callBack) {
        this.callBack = callBack;
    }
}
