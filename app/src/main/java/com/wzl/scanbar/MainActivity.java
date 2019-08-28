package com.wzl.scanbar;
import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wzl.scanbar.zxing.android.CaptureActivity;
import com.yanzhenjie.permission.AndPermission;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });
    }

    public void scan(){
        if(AndPermission.hasPermission(this, Manifest.permission.CAMERA)) {
            // 有权限，直接do anything.
            goScan();
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.CAMERA)
                    .send();
        }
    }

    /**
     * 跳转到扫码界面扫码
     */
    public void goScan(){
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 0x0000);
    }
}
