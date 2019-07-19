package com.example.noviprojekat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.IpSecManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private long currentTime;
    private Runnable runnable;
    private ArrayList<Measurement> list = new ArrayList<Measurement>();
    public SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView temp2 = findViewById(R.id.temp2);
        final TextView humid2 = findViewById(R.id.humid2);
        final TextView aq = findViewById(R.id.aq2);
        final TextView ts = findViewById(R.id.ts);
        final Button history = findViewById(R.id.button);
        final   Button share = findViewById(R.id.share);
        Context context = this;

        final Get service = RetrofitInstance.getRetrofitInstance().create(Get.class);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

                // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    1);}

//        Log.wtf("URL CALLED", res.request().url() + "");
        currentTime = System.currentTimeMillis();

        BroadcastReceiver br = new MyBroadcastReceiver();

        final Handler handler = new Handler();
        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        this.registerReceiver(br, filter);




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
                        staviUFile();
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
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected==true)
            handler.postDelayed(runnable,0);
        else{
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String measure =  sharedPreferences.getString("latest measurement", "Ne postoji sharedPreference");

        String[] separated = measure.split(",");
        temp2.setText(separated[1].substring(13));
        aq.setText(separated[2].substring(11));
        humid2.setText(separated[4].substring(10));
        ts.setText(separated[3].substring(13, separated[3].length()-1));

        }
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareMeasurement();
            }
        });




        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHistoryActivity();
            }
        });

    }



    private void staviUFile(){
        if(sharedPreferences == null)
            sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("latest measurement", list.get(list.size()-1).toString());
        editor.commit();
    }

    private void shareMeasurement(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, list.get(list.size()-1).toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
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
