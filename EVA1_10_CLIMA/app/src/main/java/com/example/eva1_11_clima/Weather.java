/**
 * EVA1_11_CLIMA - customizing the layout of a list view
 * <p>
 * written by: Luis Carlos Cruz Castillo
 * 17550448
 * APPS1
 * 01/03/2020
 */
package com.example.eva1_11_clima;

public class Weather {
    private String cityName;
    private String weatherDesc;
    private double temp;
    private int imageId;


    public Weather() {
        cityName = "";
        weatherDesc = "";
        temp = 0;
        imageId = -1;
    }

    public Weather(String cityName, int cityWeather, String weatherDesc, int imagenId) {
        this.cityName = cityName;
        this.temp = cityWeather;
        this.weatherDesc = weatherDesc;
        this.imageId = imagenId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public double getTemp() {
        return temp;
    }

    public int getImageId() {
        return imageId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
