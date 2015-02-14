package com.example.steve.weathersunshine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.steve.weathersunshine.model.WeatherOneDay;

import java.util.ArrayList;


public class MainActivity extends Activity {


    private ArrayAdapter adpter;
    public static Context context;
    ArrayList<WeatherOneDay> arrayWeather=null;


    public static Context getContext() {
        return context;
    }

    public void setArrayWeather(ArrayList<WeatherOneDay> arrayWeather) {
        this.arrayWeather = arrayWeather;
    }

    public ArrayList<WeatherOneDay> getArrayWeather() {

        return arrayWeather;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        FetchWeatherAsynTask f= new FetchWeatherAsynTask();
        String s="85281";
        f.execute(s);
        ListView list = (ListView) findViewById(R.id.listview1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), "CLICKED no:"+position, Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("weather",arrayWeather.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
