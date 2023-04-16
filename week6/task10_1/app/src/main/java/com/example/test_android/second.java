package com.example.test_android;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_android.R;

public class second extends AppCompatActivity {

    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        init();
        initLr();
    }

    public void init(){
        btnReturn = findViewById(R.id.btnReturn);
    }

    public void initLr(){
        btnReturn.setOnClickListener(v -> {
            finish();
        });
    }
}