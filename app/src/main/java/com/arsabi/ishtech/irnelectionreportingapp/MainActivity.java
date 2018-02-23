package com.arsabi.ishtech.irnelectionreportingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.arsabi.ishtech.irnelectionreportingapp.adapter.listAdapter;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        listAdapter adapter = new listAdapter(context);
        listView.setAdapter(adapter);
    }
}
