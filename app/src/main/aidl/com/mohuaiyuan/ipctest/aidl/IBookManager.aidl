// IBookManager.aidl
package com.mohuaiyuan.ipctest.aidl;

import  com.mohuaiyuan.ipctest.aidl.Book;

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
}
