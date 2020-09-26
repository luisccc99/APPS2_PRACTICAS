/**
 * EVA1_11_CLIMA - customizing the layout of a list view
 * <p>
 * written by: Luis Carlos Cruz Castillo
 * 17550448
 * APPS1
 * 01/03/2020
 */
package com.example.eva1_11_clima;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Weather> {

    private List<Weather> objects;
    private Context context;
    private int resource;

    List<Weather> citiesList = new ArrayList<>();

    //context is the activity
    public WeatherAdapter(@NonNull Context context, int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convertView represents  the created layout
        if (convertView == null) {//first time that the application is created
            convertView = ((Activity) context).getLayoutInflater().inflate(resource, parent, false); // create layout

        }
        //if the list is already created we should get back the components

        TextView txtCity;
        TextView txtTmp;
        TextView txtDesc;
        ImageView imgWeather;

        imgWeather = convertView.findViewById(R.id.imgWeather);
        txtCity = convertView.findViewById(R.id.txtCity);
        txtTmp = convertView.findViewById(R.id.txtTmp);
        txtDesc = convertView.findViewById(R.id.txtDesc);

        Weather weather = objects.get(position);
        imgWeather.setImageResource(weather.getImageId());
        txtCity.setText(weather.getCityName());
        txtTmp.setText(weather.getTemp() + "");
        txtDesc.setText(weather.getWeatherDesc());

        return convertView;
    }
}
