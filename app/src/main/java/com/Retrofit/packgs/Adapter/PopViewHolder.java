package com.Retrofit.packgs.Adapter;
import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Retrofit.packgs.databinding.ItemPopBinding;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.R;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Pop;
import com.squareup.picasso.Picasso;

public class PopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemPopBinding binding;
    TaskCallBack callBack;
    Pop pop;

    public PopViewHolder(@NonNull ItemPopBinding binding, TaskCallBack callBack) {
        super(binding.getRoot());
        this.binding = binding;
        this.callBack = callBack;
        setOnClickListener();
    }

    @SuppressLint("SetTextI18n")
    public void setUserData(Pop user) {
        this.pop = user;
        binding.emailUserView.setText(user.email);
        binding.nameUserTextView.setText(user.name);
        binding.mobileNumberUserView.setText(user.mobile);
        binding.nationalNumberUserView.setText(user.nationalNumber);
        binding.familyMembersUserView.setText(user.familyMembers);
        if (user.gender.equals("M")) {
            binding.genderUserView.setText("Male");
        } else {
            binding.genderUserView.setText("Female");
        }
        Picasso.get().load(user.imageUrl).into(binding.parsonPop);

    }


    private void setOnClickListener() {
        binding.deletePop.setOnClickListener(this);
        binding.UpdattPop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete_pop) {
            callBack.onActionCallBack(ActionType.delete, getAdapterPosition(), pop.id);

        } else if (v.getId() == R.id.UpdattPop) {
            if (callBack != null) {
                callBack.onActionCallBack(ActionType.update, getAdapterPosition(), pop.id);
            }

        }
    }
}
