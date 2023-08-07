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


public class AdvertisingFragment extends androidx.fragment.app.DialogFragment {

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "message";

    private String titles;
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

    public AdvertisingFragment() {
    }

    public static AdvertisingFragment newInstance(String title) {
        AdvertisingFragment fragment = new AdvertisingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            titles = getArguments().getString(ARG_PARAM1);
            String message = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialoge_adversting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleUp = view.findViewById(R.id.titleAd);
        EditText title = view.findViewById(R.id.Title_update);
        EditText info = view.findViewById(R.id.Info_update);

        titleUp.setText(titles);

        Button save = view.findViewById(R.id.saveAdvertising);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onPositiveClickListener.onPositiveButtOnClick(title.getText().toString(), info.getText().toString());
                dismiss();
            }
        });
    }

    public interface OnPositiveClickListener {
        void onPositiveButtOnClick(String title, String info);
    }

}