package com.example.eva_1_comunicacion_frag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class ListFragment extends Fragment {

    ListView lstViewDatos;
    String[] datos = {
            "Queen", "The Beatles", "The Who",
            "Pink Floyd", "The Rolling Stones", "Black Sabbath",
            "David Bowie", "The Beach Boys", "Michael Jackson",
            "Led Zepellin",};
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
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_list, container, false);
        lstViewDatos = frameLayout.findViewById(R.id.lst_view_datos);
        lstViewDatos.setAdapter(new ArrayAdapter<>(
                mainActivity,
                android.R.layout.simple_list_item_1,
                datos
        ));
        lstViewDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.onMessageFromFragmentToMain("LISTA", datos[position]);
            }
        });
        return frameLayout;
    }
}