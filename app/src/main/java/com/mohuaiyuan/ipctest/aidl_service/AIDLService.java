package com.mohuaiyuan.ipctest.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mohuaiyuan.ipctest.aidl.Book;
import com.mohuaiyuan.ipctest.aidl.IBookManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private static final String TAG = "IPCTest";

    //包含 Book 对象的 list
    private List<Book> bookList=new ArrayList<>();

    //由AIDL文件生成的 IBookManager
    private final IBookManager.Stub bookManager=new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            synchronized (this){
                if (bookList!=null){
                    return bookList;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if (bookList==null){
                    bookList=new ArrayList<>();
                }
                
                if (book==null){
                    Log.d(TAG, "book==null  in In 。。。 ");
                    book=new Book();
                }

                //尝试修改 book的参数 ，主要是为了观察其到客户端的反馈
                book.setName("default");
                
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("first Name");
        book.setId(-1);
        bookList.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
