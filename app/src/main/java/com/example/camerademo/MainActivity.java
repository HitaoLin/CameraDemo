package com.example.camerademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCamera;
    private Button bt_select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        btCamera = (Button) findViewById(R.id.bt_camera);
        bt_select = findViewById(R.id.bt_select);

        btCamera.setOnClickListener(this);
        bt_select.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_camera://相册
                startActivity(new Intent(this, CameraActivity.class));
                break;

            case R.id.bt_select://图片选择框架
                startActivity(new Intent(this, SelectActivity.class));
                break;
        }
    }
}
