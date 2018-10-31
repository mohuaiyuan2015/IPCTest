package com.mohuaiyuan.ipctest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mohuaiyuan.ipctest.manager.UserManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "IPCTest";
    
    private Button openSecondActivity;
    private Button openAIDLClientActivity;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        initUI();
        initData();
        initListener();



    }

    private void initUI() {
        openSecondActivity=findViewById(R.id.openSecondActivity);
        openAIDLClientActivity=findViewById(R.id.openAIDLClientActivity);

    }
    private void initData() {

        //test
//        UserManager.sUserId=2;
        Log.d(TAG, "MainActivity UserManager.sUserId: "+UserManager.sUserId);

    }
    private void initListener() {
        openSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "openSecondActivity onClick: ");
                Intent intent=new Intent(context,SecondActivity.class);
                context.startActivity(intent);
            }
        });

        openAIDLClientActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "openAIDLClientActivity onClick: ");
                Intent intent=new Intent(context,AIDLClientActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
