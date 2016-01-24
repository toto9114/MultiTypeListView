package com.example.sony.multitypelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    MyAdapter myAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object data = listView.getItemAtPosition(position);
//                    Toast.makeText(MainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }
        });
        initData();
    }
    public void initData(){
        Random r = new Random();
        for(int i=0; i<6; i++){
           int itemCount = 2+ r.nextInt(4);
            for(int j =0; j<itemCount; j++){
                myAdapter.add("group" + i, "child" + j);
            }
        }
    }
}
