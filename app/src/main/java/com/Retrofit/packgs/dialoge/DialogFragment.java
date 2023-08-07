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


public class DialogFragment extends androidx.fragment.app.DialogFragment {


    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "message";


    private String title;
    private OnPositiveClickListener onPositiveClickListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPositiveClickListener) {
            onPositiveClickListener = (OnPositiveClickListener) context;
        } else
            throw new RuntimeException("please implement listener: positive");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onPositiveClickListener = null;
    }

    public DialogFragment() {
    }

    public static DialogFragment newInstance(String title) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.dialoge_emplloyee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleUp = view.findViewById(R.id.title);
        EditText nameUpdate = view.findViewById(R.id.name_update);
        EditText mobileUpdate = view.findViewById(R.id.mobile_update);
        EditText nationalUpdate = view.findViewById(R.id.national_update);
        titleUp.setText(title);

        Button save = view.findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onPositiveClickListener.onPositiveButtOnClicked(nameUpdate.getText().toString(), mobileUpdate.getText().toString(), nationalUpdate.getText().toString());
                dismiss();
            }
        });

    }

    public interface OnPositiveClickListener {
        void onPositiveButtOnClicked(String name, String phone, String national);

    }


}