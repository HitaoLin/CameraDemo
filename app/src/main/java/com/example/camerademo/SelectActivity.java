package com.example.camerademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.camerademo.utils.ConfigValue;
import com.example.camerademo.utils.SPConfig;
import com.example.camerademo.utils.SPUtils;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;

/**
 * date:20190527
 * github: https://github.com/wildma/PictureSelector
 */
public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btSelect;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        initView();
        String path = (String) SPUtils.get(this, SPConfig.PICTUREPATH, "");
        ConfigValue.Picture_Path = (String) SPUtils.get(this, SPConfig.PICTUREPATH, "");
        image.setImageBitmap(ImageUtils.getBitmap(path));


    }

    private void initView() {

        btSelect = (Button) findViewById(R.id.bt_select);
        image = (ImageView) findViewById(R.id.image);

        btSelect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_select:
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                image.setImageBitmap(ImageUtils.getBitmap(picturePath));

                SPUtils.put(this, SPConfig.PICTUREPATH, picturePath);
                ConfigValue.Picture_Path = picturePath;

            }
        }
    }

}
