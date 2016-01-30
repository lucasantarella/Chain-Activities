package com.example.lucasantarella.chainacitivies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TwoActivity extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        imageView = (ImageView) findViewById(R.id.twoImageView);
        editText = (EditText) findViewById(R.id.twoEditText);

        Bitmap bmp = null;
        String filename = getIntent().getStringExtra("image");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();

            imageView.setImageBitmap(bmp);
            editText.setText(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClickSave(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageName", editText.getText().toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
