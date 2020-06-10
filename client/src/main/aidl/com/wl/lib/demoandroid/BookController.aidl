// Book.aidl
package com.wl.lib.demoandroid;
import com.wl.lib.demoandroid.Book;

// Declare any non-default types here with import statements

interface BookController{

    List<Book> getBookList();

    void addBookInout(inout Book book);

}