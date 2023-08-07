package com.Retrofit.packgs.dialoge;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.Retrofit.packgs.R;

public class UpdatePop extends androidx.fragment.app.DialogFragment{

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "message";

    private String title;
    private OnPositiveClickListeners onPositiveClickListeners;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPositiveClickListeners) {
            onPositiveClickListeners = (OnPositiveClickListeners) context;
        } else
            throw new RuntimeException("please implement listener: positive");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onPositiveClickListeners = null;
    }

    public UpdatePop() {
    }

    public static UpdatePop newInstance(String title) {
        UpdatePop dialogUpdateUser = new UpdatePop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        dialogUpdateUser.setArguments(args);
        return dialogUpdateUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            String message = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doaloge_pop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleUp = view.findViewById(R.id.title);
        EditText nameUpdate = view.findViewById(R.id.name_user_update);
        EditText mobileUpdate = view.findViewById(R.id.mobile_user_update);
        EditText nationalUpdate = view.findViewById(R.id.national_user_update);
        EditText family_members = view.findViewById(R.id.family_user_update);
        EditText gender = view.findViewById(R.id.gender_user_update);

//        titleUp.setText(title);

        Button save = view.findViewById(R.id.save_update);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onPositiveClickListeners.onPositiveButtOnClicked(nameUpdate.getText().toString(),mobileUpdate.getText().toString() ,nationalUpdate.getText().toString() ,  family_members.getText().toString(),  gender.getText().toString());
                dismiss();
            }
        });

    }

    public interface OnPositiveClickListeners {
        void onPositiveButtOnClicked(String name,  String mobile , String national , String family, String gender);
    }

}