package com.example.steve.weathersunshine.util;

import com.example.steve.weathersunshine.model.WeatherOneDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Steve on 2/8/2015.
 */
public class JsonParser {

    public static WeatherOneDay parseOneDayForcast(String s) throws JSONException {

        WeatherOneDay w = new WeatherOneDay();

        JSONObject obj = new JSONObject(s);
        JSONArray arrWeather = obj.getJSONArray("weather");
        JSONObject objTemp = obj.getJSONObject("temp");
        w.setCalendar(obj.getLong("dt"));
        w.setTempMax(objTemp.getString("max"));
        w.setTempMin(objTemp.getString("min"));
        w.setTempDay(objTemp.getString("day"));
        w.setWeatherMain(arrWeather.getJSONObject(0).getString("main"));
        w.setHumidity(obj.getString("humidity"));
        w.setPressure(obj.getString("pressure"));

        return w;
    }

    public static ArrayList<WeatherOneDay> parse7Day(String s) {
        ArrayList<WeatherOneDay> array = new ArrayList<WeatherOneDay>();
        try {
            JSONObject obj = new JSONObject(s);
            JSONArray jsonArr = obj.getJSONArray("list");

            for (int i = 0; i < jsonArr.length(); i++) {
                array.add(parseOneDayForcast(jsonArr.getJSONObject(i).toString()));
                //array[i].setWeatherMain(arr.getJSONObject(i).toString());
//                array[i]=new WeatherOneDay();
//                array[i].setWeatherMain(jsonArr.getJSONObject(i).toString());

            }
//            array[0]=new WeatherOneDay();
//            array[0].setWeatherMain("arr len="+jsonArr.length());
        } catch (Exception e) {
            System.err.println(e);
        }
        return array;
    }
}
