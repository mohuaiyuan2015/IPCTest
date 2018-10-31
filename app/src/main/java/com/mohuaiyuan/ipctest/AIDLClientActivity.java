package com.mohuaiyuan.ipctest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mohuaiyuan.ipctest.aidl.Book;
import com.mohuaiyuan.ipctest.aidl.IBookManager;

import java.util.List;

public class AIDLClientActivity extends AppCompatActivity {

    private static final String TAG = "IPCTest";

    /**
     * 由AIDL文件生成的Java类
     */
    private IBookManager bookManager;

    /**
     * 标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
     */
    private boolean isBound = false;

    /**
     * 包含Book对象的list
     */
    private List<Book> mBooks;

    private Button getBookListBtn;
    private Button addBookBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient);

        initUI();
        initData();
        initListener();

    }

    private void initUI() {
        getBookListBtn=findViewById(R.id.getBookListBtn);
        addBookBtn=findViewById(R.id.addBookBtn);
    }

    private void initData() {

    }

    private void initListener() {

        getBookListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "getBookListBtn onClick: ");
                addBook();

            }
        });

    }

    private void addBook() {
        Log.d(TAG, "addBook: ");
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!isBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bookManager == null) return;

        Book book = new Book();
        book.setName("APP研发录In");
        book.setId(87);
        try {
            bookManager.addBook(book);
            Log.d(TAG, "book.toString(): "+book.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试与服务端建立连接
     */
    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.mohuaiyuan.ipctest.aidl");
        intent.setPackage("com.mohuaiyuan.ipctest");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    private ServiceConnection mServiceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            bookManager = IBookManager.Stub.asInterface(iBinder);
            isBound = true;

            if (bookManager != null) {
                try {
                    mBooks = bookManager.getBookList();
                    Log.d(TAG, "mBooks: "+mBooks.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound=false;
        }
    };

}
