package com.example.admin.qimoapp.Login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    //构造方法（这个方法必须有）
    //context上下文，name数据库名称，factory为了创建cursor对象默认为空，version数据库版本
    public MyDataHelper(Context context, String name) {
        super(context, name, null, 1);
        // TODO Auto-generated constructor stub
    }

    //第一次创建数据库的时候会调用该方法
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
        arg0.execSQL("create table if not exists user3 (_id integer primary key autoincrement,username text not null,pwd text not null,question text not null,answer text not null,nickname text,sex text,location text,pic BLOB)");
    }

    //当更新数据库（数据库版本更新）的时候会调用该方法
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
