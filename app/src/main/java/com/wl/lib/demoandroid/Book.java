package com.wl.lib.demoandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：author wl
 * 时间：2019/8/1:10:50
 * 说明：
 */
public class Book implements Parcelable {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Book(Parcel in) {
        name = in.readString();
    }

    public void readFromParcel(Parcel dest) {
        name = dest.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
