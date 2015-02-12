package com.example.steve.weathersunshine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


public class MyActivity extends Activity {


    private ArrayAdapter adpter;
    public static Context context;
    public static Context getContext() {
        return context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        String strs[]={"haha","huhu"};
//        ArrayList<String> array= new ArrayList<String>(Arrays.asList(strs));
//        adpter=new ArrayAdapter(this,R.layout.list_item,R.id.textView1,array);
//        ListView lv1=(ListView)findViewById(R.id.listview1);
//        lv1.setAdapter(adpter);

        FetchWeatherAsynTask f= new FetchWeatherAsynTask();
        String s="85281";
        f.execute(s);
        //Toast.makeText(context, "haha", Toast.LENGTH_LONG).show();
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
