package com.tao.ndklearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.orbyun.rtmp.NativeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NativeUtils.method();
    }
}
