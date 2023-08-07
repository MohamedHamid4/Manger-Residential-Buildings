package com.Retrofit.packgs.views.pop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Retrofit.packgs.databinding.ActivityPopBinding;
import com.Retrofit.packgs.Adapter.PopViewAdapter;
import com.Retrofit.packgs.api.controllers.PopApiController;
import com.Retrofit.packgs.dialoge.UpdatePop;
import com.Retrofit.packgs.enums.ActionType;
import com.Retrofit.packgs.interfaces.ListCalBack;
import com.Retrofit.packgs.interfaces.ProcessCallBack;
import com.Retrofit.packgs.interfaces.TaskCallBack;
import com.Retrofit.packgs.model.Pop;
import com.Retrofit.packgs.R;

import java.util.ArrayList;
import java.util.List;

public class PopActivity extends AppCompatActivity implements TaskCallBack, View.OnClickListener, UpdatePop.OnPositiveClickListeners {
    ActivityPopBinding binding;
    List<Pop> users = new ArrayList<>();
    Pop pop;
    PopViewAdapter adapter;
    int id , position ;
    String name ,phone ,national , family_members , gender ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void setOnclickListener() {
        binding.userFloatingActionButton.setOnClickListener(this);
    }

    public void initializeView() {
        setAdapter();
        getPop();
        setOnclickListener();

    }

    @Override
    public void onActionCallBack(ActionType actionType, int positon, int id) {
        if (actionType == ActionType.delete) {
            users.remove(positon);
            adapter.notifyItemRemoved(positon);
            this.position = positon;
            this.id = id;
            deleteUser();
        } else if (actionType == ActionType.update) {
            UpdatePop dialogUpdateUser = UpdatePop.newInstance("Update Pop ");
            dialogUpdateUser.show(getSupportFragmentManager(), null);
            this.id = id;
        }
    }


    private void setAdapter() {
        adapter = new PopViewAdapter(users);
        binding.userRv.setAdapter(adapter);
        adapter.setCallBack(this::onActionCallBack);
        binding.userRv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getPop() {
        PopApiController controller = new PopApiController();
        controller.getUsers(new ListCalBack<Pop>() {
            @Override
            public void onSuccess(List<Pop> list) {
                users.clear();
                users.addAll(list);
                adapter.notifyItemChanged(0, users.size());

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void deleteUser() {
        PopApiController controller = new PopApiController();
        Pop user = new Pop();
        user.id = id;
        controller.deleteUser(user, new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(PopActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(PopActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePop(Pop pop) {
        this.pop = pop;
        PopApiController userApiController = new PopApiController();
        pop.id = id;
        pop.name = name ;
        pop.mobile = phone;
        pop.nationalNumber = national;
        pop.gender = gender;

        userApiController.update(pop, new ProcessCallBack() {
            @Override
            public void onSuccess(String message) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(PopActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(PopActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPositiveButtOnClicked(String name, String mobile, String national, String family, String gender) {
        this.name = name;
        this.phone = mobile;
        this.national = national;
        this.family_members = family;
        this.gender = gender;
        updatePop(users.get(position));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_floatingActionButton) {
            Intent intent = new Intent(getApplicationContext(), PopAddActivity.class);
            startActivity(intent);
        }

    }


}