package com.example.steve.weathersunshine.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Steve on 2/8/2015.
 */
public class WeatherOneDay {

    private String pressure;
    private String humidity;
    private String tempDay;
    private String tempMin;
    private String tempMax;
    private String weatherMain;
    private Calendar  calendar ;

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    public void setCalendar(long dt) {
        Date date = new Date(dt*1000);
        Calendar cal=Calendar.getInstance();
        DateFormat format=new SimpleDateFormat("yyyy/mm/dd");
        format.format(date);
        cal=format.getCalendar();
        this.calendar = cal;
    }

    public int getDay(){
        return this.calendar.get(Calendar.DATE);
    }
    public String getMonth(){
//        return this.calendar.get(Calendar.MONTH);
        return (new SimpleDateFormat("MMM").format(calendar.getTime())).toString();
    }
    public Calendar getCalendar() {

        return calendar;
    }

    public String getWeatherMain() {
        if (weatherMain==null)
            weatherMain="weather main is null";
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
