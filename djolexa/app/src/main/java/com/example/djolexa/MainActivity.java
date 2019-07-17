package com.example.djolexa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.*;

import retrofit2.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyEndpoint apiService = retrofit.create(MyEndpoint.class);

        final TextView textView = findViewById(R.id.text);

        Call<MenuWrapperClass> call = apiService.getMenuClass();
        call.enqueue(new Callback<MenuWrapperClass>() {
            @Override
            public void onResponse(Call<MenuWrapperClass> call, Response<MenuWrapperClass> response) {
                int statusCode = response.code();
                MenuWrapperClass menu = response.body();
                textView.setText(menu.toString());
            }

            @Override
            public void onFailure(Call<MenuWrapperClass> call, Throwable t) {
                // Log error here since request failed
            }
        });
    */

    }



    @Override
    protected void onResume(){
        super.onResume();
        Log.d("lifeCycle", "onResume1()");
        /*Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifeCycle", "onStart1()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifeCycle", "onPause1()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifeCycle", "onDestroy()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifeCycle", "onStop1()");
    }
}
