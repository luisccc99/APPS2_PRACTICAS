/**
 * EVA1_11_CLIMA - customizing the layout of a list view
 * <p>
 * written by: Luis Carlos Cruz Castillo
 * 17550448
 * APPS1
 * 01/03/2020
 */
package com.example.eva1_11_clima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView txtCity;
    TextView txtTmp;
    TextView txtDesc;
    ImageView imgWeather;
    ListView lstClima;
    List<Weather> cities = new ArrayList<>();


    public static class NukeSSLCerts {
        protected static final String TAG = "NukeSSLCerts";

        public static void nuke() {
            try {
                TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                                return myTrustedAnchors;
                            }

                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }
                };

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstClima = findViewById(R.id.lstClima);
        NukeSSLCerts.nuke();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=439d4b804bc8187953eb36d2a8c26a02",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("list");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                Weather city = new Weather();
                                city.setCityName(object.getString("name"));
                                JSONObject main = object.getJSONObject("main");
                                city.setTemp(main.getDouble("temp"));
                                JSONArray weather = object.getJSONArray("weather");
                                JSONObject cityWeather = weather.getJSONObject(0);
                                city.setWeatherDesc(cityWeather.getString("description"));
                                int id = cityWeather.getInt("id");
                                if (id < 300) {
                                    city.setImageId(R.drawable.thunderstorm);
                                } else if (id < 400) { // showers
                                    city.setImageId(R.drawable.light_rain);
                                } else if (id < 600) { // rainy
                                    city.setImageId(R.drawable.rainy);
                                } else if (id < 700) { // snow
                                    city.setImageId(R.drawable.snow);
                                } else if (id < 800) { // clever
                                    city.setImageId(R.drawable.sunny);
                                } else if (id < 900) { // gray
                                    city.setImageId(R.drawable.cloudy);
                                } else {
                                    city.setImageId(R.drawable.tornado);
                                }
                                cities.add(city);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        lstClima.setAdapter(
                                new WeatherAdapter(MainActivity.this,
                                        R.layout.mi_layout,
                                        cities));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }
}
