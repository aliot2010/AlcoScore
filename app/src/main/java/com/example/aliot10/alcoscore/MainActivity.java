package com.example.aliot10.alcoscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private String[] titles;
    private ListView titleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = getResources().getStringArray(R.array.titles);
        titleList = (ListView) findViewById(R.id.drawer);
        titleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        
    }
}
