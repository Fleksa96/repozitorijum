package com.example.noviprojekat;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private long currentTime;
    private Runnable runnable;
    private ArrayList<Measurement> list = new ArrayList<Measurement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView temp2 = findViewById(R.id.temp2);
        final TextView humid2 = findViewById(R.id.humid2);
        final TextView aq = findViewById(R.id.aq2);
        final TextView ts = findViewById(R.id.ts);
        final Button history = findViewById(R.id.button);

        final Get service = RetrofitInstance.getRetrofitInstance().create(Get.class);


//        Log.wtf("URL CALLED", res.request().url() + "");
        currentTime = System.currentTimeMillis();


        final Handler handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
//                handler.removeCallbacks(this);
                handler.postDelayed(this,5000);
                Call<Measurement> res = service.get();

                res.enqueue(new Callback<Measurement>() {
                    @Override
                    public void onResponse(Call<Measurement> call, Response<Measurement> response) {
                        Measurement menu = response.body();
                        temp2.setText(menu.getTemperature()+"°C");
                        humid2.setText(menu.getHumidity()+"%");
                        aq.setText(menu.getPollution());
                        ts.setText(menu.getTimestamp());
                        list.add(menu);
                    }

                    @Override
                    public void onFailure(Call<Measurement> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

               /* if(System.currentTimeMillis()-currentTime>5000){
                    handler.postDelayed(runnable, 5000);
                    currentTime = System.currentTimeMillis();
                }*/


            }
        };

        handler.postDelayed(runnable,0);



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHistoryActivity();
            }
        });

    }

    private void startHistoryActivity(){
        Intent intent = new Intent(this, ShowHistory.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("lista",list);
//        intent.putExtras(bundle);
        intent.putParcelableArrayListExtra("lista", list);
        startActivity(intent);
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
