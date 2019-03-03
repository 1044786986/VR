package com.example.ljh.vr.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ljh.vr._application.MyApplication;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String SQLITE_NAME = "ljh";
    private static final int VERSION = 1;
    private SQLiteDatabase mSqliteDatabase;
    private static SQLiteHelper mSQLiteHelper;

    public static SQLiteHelper getInstance() {
        if (mSQLiteHelper == null) {
            synchronized (SQLiteHelper.class) {
                if (mSQLiteHelper == null) {
                    mSQLiteHelper = new SQLiteHelper(MyApplication.getInstance(), null);
                }
            }
        }
        return mSQLiteHelper;
    }

    public SQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        this(context, SQLITE_NAME, factory, VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE search_log(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "logistics_number VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE recently_city(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "city VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i == 1 && i1 == 2) {
            KLog.i("数据库升级啦");
        }
    }

    public List<String> getRecentlyCity() {
        mSqliteDatabase = mSQLiteHelper.getReadableDatabase();
        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM recently_city ORDER BY _id DESC", null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("city")));
        }
        mSqliteDatabase.close();
        return list;
    }

    public synchronized void addRecentlyCity(String city) {
        mSqliteDatabase = getReadableDatabase();
        mSqliteDatabase.execSQL("DELETE FROM recently_city WHERE city ='" + city + "'");
        int count = mSqliteDatabase.rawQuery("SELECT * FROM recently_city",null).getCount();
        if(count == 9){
            mSqliteDatabase.execSQL("DELETE FROM recently_city ORDER BY _id LIMIT 1");
        }
        ContentValues contentValues = new ContentValues();

        contentValues.put("city",city);
        mSqliteDatabase.insert("recently_city",null,contentValues);
        mSqliteDatabase.close();
    }

}
