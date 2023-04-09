//실습 8_2

package com.LDM.HW_8_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button prev, next;
    myPictureView view;
    static int curNum = 0;
    String[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        prev = (Button) findViewById(R.id.btnPrev);
        next = (Button) findViewById(R.id.btnNext);
        view = (myPictureView) findViewById(R.id.myPictureView);

        imageFiles = new String[5];
        imageFiles[0] = "/sdcard/Pictures/Messenger/1681030445152.jpg";
        imageFiles[1] = "/sdcard/Pictures/Messenger/1681030445152.jpg";
        imageFiles[2] = "/sdcard/Pictures/Messenger/1681030445152.jpg";
        imageFiles[3] = "/sdcard/Pictures/Messenger/1681030445152.jpg";
        imageFiles[4] = "/sdcard/Pictures/Messenger/1681030445152.jpg";

        imageFname = imageFiles[curNum];
        view.imagePath = imageFname;

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum <= 1) {
                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    view.imagePath=imageFname;
                    view.invalidate();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum >= imageFiles.length-1) {
                    Toast.makeText(getApplicationContext(), "마지막 그림입니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    curNum++;
                    imageFname=imageFiles[curNum].toString();
                    view.imagePath=imageFname;
                    view.invalidate();
                }
            }
        });
    }
}