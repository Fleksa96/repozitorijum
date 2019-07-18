package com.example.noviprojekat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowHistory extends AppCompatActivity {

    private ArrayList<Measurement> list = new ArrayList<Measurement>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_history);

//        Bundle b = this.getIntent().getExtras();
//        list = (ArrayList<Measurement>) b.getSerializable("lista");
        list = getIntent().getParcelableArrayListExtra("lista");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(list);

        recyclerView.setAdapter(mAdapter);







    }

    protected void onStart(){
        super.onStart();

        Log.d("lifecycle","onStart");

    }
    protected void onResume(){

        super.onResume();
        Log.d("lifecycle","onResume");
/*        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)

            }
        });*/

    }
    protected void onPause(){
        super.onPause();
        Log.d("lifecycle","onPause");


    }
    protected void onStop(){
        Log.d("lifecycle","onStop");

        super.onStop();

    }

    protected void onDestroy(){
        Log.d("lifecycle","onDestroy");

        super.onDestroy();

    }
}
