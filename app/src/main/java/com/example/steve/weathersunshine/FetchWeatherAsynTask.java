package com.example.steve.weathersunshine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.steve.weathersunshine.model.WeatherOneDay;
import com.example.steve.weathersunshine.util.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Steve on 2/8/2015.
 */
public class FetchWeatherAsynTask extends AsyncTask<String, Void, ArrayList<WeatherOneDay>> {
    @Override
    protected void onPostExecute(ArrayList<WeatherOneDay> arrayWeather) {
        super.onPostExecute(arrayWeather);
        Context context= MainActivity.getContext();
        MainActivity  activity= (MainActivity)context;
        activity.setArrayWeather(arrayWeather);
        int len=arrayWeather.size();
        String strs[]= new String[len];


        for (int i=0;i<len;i++){
            StringBuffer sb= new StringBuffer();
            WeatherOneDay w= arrayWeather.get(i);
//            sb.append(w.getDate().getMonth()+","+w.getDate().getDay()+":");
            sb.append(w.getMonth()+w.getDay()+":");
            sb.append(w.getWeatherMain()+",");
            sb.append("max:"+w.getTempMax()+",");
            sb.append("min:"+w.getTempMin());
            strs[i]=sb.toString();
        }
        ArrayList<String> array= new ArrayList<String>(Arrays.asList(strs));
        ArrayAdapter adapter;
        adapter=new ArrayAdapter(activity,R.layout.list_item,R.id.textView1,array);
        ListView lv1=(ListView)activity.findViewById(R.id.listview1);
        lv1.setAdapter(adapter);
        //Toast.makeText(context,"haha "+s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected ArrayList<WeatherOneDay> doInBackground(String... strings) {
        // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
        if (strings.length != 1) {
            Log.d("meng", "error input parameter, length should be 1");
            return null;
        }
        String uriBase = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s,USA&mode=json&units=metric&cnt=7";
        String struri = String.format(uriBase, strings[0]);
        Log.d("meng", struri);
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

// Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL(struri);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = "input stream null";
                Log.d("meng",forecastJsonStr);
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = "buffer len=0 " + strings[0];
                Log.d("meng",forecastJsonStr);
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            forecastJsonStr = "IO error";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        ArrayList<WeatherOneDay> sarrayWeather = JsonParser.parse7Day(forecastJsonStr);
        return sarrayWeather;
    }


}
