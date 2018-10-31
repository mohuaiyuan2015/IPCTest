package com.mohuaiyuan.ipctest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mohuaiyuan.ipctest.manager.UserManager;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "IPCTest";

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context=this;

        initUI();
        initData();
        initListener();

    }

    private void initUI() {
    }
    private void initData() {

        Log.d(TAG, "SecondActivity UserManager.sUserId : "+ UserManager.sUserId);
    }
    private void initListener() {
    }
}
