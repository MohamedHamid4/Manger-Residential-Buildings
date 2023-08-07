package com.Retrofit.packgs.Adapter;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.R;
import com.Retrofit.packgs.databinding.ItemAdvertisingBinding;
import com.Retrofit.packgs.databinding.ItemEmployeeBinding;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.AdMangment;
import com.Retrofit.packgs.model.Employee;
import com.squareup.picasso.Picasso;


public class AdMangmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemAdvertisingBinding binding;
    TaskCallBack callBack;
    AdMangment adMangment;



    public AdMangmentViewHolder(@NonNull ItemAdvertisingBinding binding, TaskCallBack callBack) {
        super(binding.getRoot());
        this.binding = binding;
        this.callBack = callBack;
        setOnClickListener();
    }
    public void setEmployee (AdMangment adMangment){
        this.adMangment = adMangment;
        binding.tvTitle.setText(adMangment.title);
        binding.tvDescription.setText(adMangment.info);
        Picasso.get().load(adMangment.imageUrl).into(binding.parsonEmp);
    }

    private void setOnClickListener(){
        binding.deleteEmp.setOnClickListener(this);
        binding.updatePop1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete_emp){
                callBack.onActionCallBack(ActionType.delete,getAdapterPosition(),adMangment.id);
            }else if (view.getId() == R.id.updatePop1){
                if (callBack != null){
                    callBack.onActionCallBack(ActionType.update,getAdapterPosition(),adMangment.id);
                }
            }
        }

}