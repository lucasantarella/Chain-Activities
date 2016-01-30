package com.example.lucasantarella.chainacitivies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OneActivity extends AppCompatActivity {
    static final int REQUSET_ONE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        textview = (TextView) findViewById(R.id.oneTextView);
    }

    public void takePicture(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    try {
                        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                        assert imageBitmap != null;
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        stream.close();
                        imageBitmap.recycle();
                        Intent intent = new Intent(this, TwoActivity.class);
                        intent.putExtra("image", filename);
                        startActivityForResult(intent, REQUSET_ONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUSET_ONE:
                    String newName = data.getStringExtra("imageName");
                    textview.setText(newName);
                    break;
            }
        }
    }
}
