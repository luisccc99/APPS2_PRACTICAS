package com.example.eva_1_comunicacion_frag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DataFragment extends Fragment {

    TextView txtDatos;
    MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_data, container, false);
        txtDatos = linearLayout.findViewById(R.id.txt_datos);
        Button button = linearLayout.findViewById(R.id.btn_data);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mainActivity.onMessageFromFragmentToMain("DATA", "Mensaje del fragmento datos");
            }
        });
        return linearLayout;
    }

    public void onMessageFromMainToFragment(String info) {
        txtDatos.setText(info);
    }
}