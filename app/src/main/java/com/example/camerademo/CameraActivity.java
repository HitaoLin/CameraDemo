package com.example.camerademo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btnGroup;
    private Button selectBtn;//选择图片
    private Button grayBtn;//灰度化
    private ImageView img;

    int REQUEST_CODE_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initView();

    }

    private void initView() {

        btnGroup = (LinearLayout) findViewById(R.id.btn_group);
        selectBtn = (Button) findViewById(R.id.select_btn);
        grayBtn = (Button) findViewById(R.id.gray_btn);
        img = (ImageView) findViewById(R.id.img);

        selectBtn.setOnClickListener(this);

    }

    private void requirePermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            if (ActivityCompat.checkSelfPermission(CameraActivity.this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CameraActivity.this, new String[]{permission},123);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_btn:
                Intent albumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                albumIntent.addCategory(Intent.CATEGORY_OPENABLE);           //调用相册
                albumIntent.setType("image/jpeg");
                startActivityForResult(albumIntent, REQUEST_CODE_IMAGE);

                break;
        }
    }
}
