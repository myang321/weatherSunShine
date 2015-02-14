package com.example.steve.weathersunshine;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steve.weathersunshine.model.WeatherOneDay;

public class DetailActivity extends Activity {

    private static WeatherOneDay w = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        getActionBar().setTitle("Details");
        Intent intent = getIntent();
        w = (WeatherOneDay) intent.getParcelableExtra("weather");
//        Toast.makeText(this, "in detail:" + w.getMonth() + ":" + w.getDay(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView textViewDate;
            TextView textViewMax;
            TextView textViewMin;
            TextView textViewMain;
            TextView textViewHumidity;
            ImageView imageView1;
            textViewDate = (TextView) rootView.findViewById(R.id.textViewDate);
            textViewMax = (TextView) rootView.findViewById(R.id.textViewMax);
            textViewMin = (TextView) rootView.findViewById(R.id.textViewMin);
            textViewMain = (TextView) rootView.findViewById(R.id.textViewMain);
            textViewHumidity= (TextView) rootView.findViewById(R.id.textViewHumidity);
            imageView1=(ImageView)rootView.findViewById(R.id.imageView1);
            textViewDate.setText(w.getMonth() + " " + w.getDay());
            textViewMax.setText(w.getTempMax());
            textViewMin.setText(w.getTempMin());
            textViewMain.setText(w.getWeatherMain());
            textViewHumidity.setText("Humidity:" + w.getHumidity());
//            imageView1.setd
            return rootView;
        }
    }
}
