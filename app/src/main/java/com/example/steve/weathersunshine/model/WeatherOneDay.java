package com.example.steve.weathersunshine.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Steve on 2/8/2015.
 */
public class WeatherOneDay implements Parcelable {

    private String pressure;
    private String humidity;
    private String tempDay;
    private String tempMin;
    private String tempMax;
    private String weatherMain;
    private Calendar calendar;
    private long dt;

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<WeatherOneDay> CREATOR = new Parcelable.Creator<WeatherOneDay>() {
        public WeatherOneDay createFromParcel(Parcel in) {
            return new WeatherOneDay(in);
        }

        public WeatherOneDay[] newArray(int size) {
            return new WeatherOneDay[size];
        }
    };

    public WeatherOneDay(Parcel in) {
        this(in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readInt());
    }

    public WeatherOneDay() {

    }

    public WeatherOneDay(String pressure, String humidity, String tempDay, String tempMin, String tempMax, String weatherMain, long dt) {
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempDay = tempDay;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.weatherMain = weatherMain;
        this.dt = dt;
        this.setCalendar(dt);
        Log.d("meng","dt="+dt);
        Log.d("meng","main="+weatherMain);
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getDt() {

        return dt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pressure);
        parcel.writeString(humidity);
        parcel.writeString(tempDay);
        parcel.writeString(tempMin);
        parcel.writeString(tempMax);
        parcel.writeString(weatherMain);
        parcel.writeLong(dt);
        Log.d("meng","write to parcel dt="+dt);
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCalendar(long dt) {
        this.setDt(dt);
        Date date = new Date(dt * 1000);
        Calendar cal;//=Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        format.format(date);
        cal = format.getCalendar();
        this.calendar = cal;
    }

    public int getDay() {
        if (calendar == null)
            return -1;
        return this.calendar.get(Calendar.DATE);
    }

    public String getMonth() {
//        return this.calendar.get(Calendar.MONTH);
        if (calendar == null)
            return "calendar is null";
        return (new SimpleDateFormat("MMM").format(calendar.getTime())).toString();
    }

    public Calendar getCalendar() {

        return calendar;
    }

    public String getWeatherMain() {
        if (weatherMain == null)
            weatherMain = "weather main is null";
        return weatherMain;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTempDay() {
        return tempDay;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTempDay(String tempDay) {
        this.tempDay = tempDay;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }


}
