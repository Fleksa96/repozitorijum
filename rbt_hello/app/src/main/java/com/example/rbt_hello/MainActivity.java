package com.example.rbt_hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
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
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText textField;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private EditText mEditText;
    private TextView mTextView;
    private Button toggleOn;
    private Button toggleOff;
    private Button switchImg;
    private Switch toggle;
    private ImageView imageView;
    private boolean turn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);
        intent = new Intent(MainActivity.this,SecondActivity.class);

        mEditText = findViewById(R.id.edit);
        mTextView = findViewById(R.id.TextView);
        toggleOff = findViewById(R.id.button2);
        toggleOn  = findViewById(R.id.button);
        toggle = findViewById(R.id.toggle);
        switchImg = findViewById(R.id.btn);
        imageView = findViewById(R.id.image);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextView.setText(editable.toString());
            }
        });
        Log.d("lifecycle","onCreate");

        toggleOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle.setChecked(true);
            }
        });

        toggleOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle.setChecked(false);
            }
        });

        switchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn == true)
                    imageView.setImageResource(R.mipmap.hamburger);
                else
                    imageView.setImageResource(R.mipmap.ic_launcher);
                turn = !turn;
            }
        });


        GetMenu service = RetrofitInstance.getRetrofitInstance().create(GetMenu.class);

        Call<MenuEncapsulated> res = service.getMenu();

        Log.wtf("URL CALLED", res.request().url() + "");

        res.enqueue(new Callback<MenuEncapsulated>() {
            @Override
            public void onResponse(Call<MenuEncapsulated> call, Response<MenuEncapsulated> response) {
                MenuEncapsulated menu = response.body();
            }

            @Override
            public void onFailure(Call<MenuEncapsulated> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sendMessage(){
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(new Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT, "Hej ja sam poruka\n").setType("text/plain"));
        }
    }


    private View.OnClickListener doShare = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            //createAlarm(textField.getText().toString(),5,5);
            try {
                capture();
            }catch (IOException e){

            }

        }
    };


    private void capture() throws IOException{


        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            if (!hasPermissions(mContext, PERMISSIONS))
//                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST)

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                    photoFile = createImageFile();
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }

    }


    private File createImageFile() throws IOException{
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );

        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    protected void onStart(){
        super.onStart();

        Log.d("lifecycle","onStart");

    }
    protected void onResume(){

        super.onResume();
        Log.d("lifecycle","onResume");


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
