package com.example.ljh.vr.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper{
    public static final String SQLITE_NAME = "ljh";
    private static final int VERSION = 1;
    private SQLiteDatabase mSqliteDatabase;

    public SQLiteHelper(Context context,SQLiteDatabase.CursorFactory factory){
        this(context,SQLITE_NAME,factory,VERSION);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE search_log(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "logistics_number VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE recently(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "city VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i == 1 && i1 == 2){
            KLog.i("数据库升级啦");
        }
    }

    public List<String> getRecentlyCity(){
        mSqliteDatabase = getReadableDatabase();
        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM recently_city LIMIT 3",null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex("city")));
        }
        mSqliteDatabase.close();
        return list;
    }

    public void addRecentlyCity(String city){
        mSqliteDatabase = getReadableDatabase();
        String sql = "INSERT INTO recently VALUES('','"+city+"')";
        mSqliteDatabase.execSQL(sql,null);
        mSqliteDatabase.close();
    }

//    public List<SearchLogBean> getSearchLog(){
//        mSqliteDatabase = getReadableDatabase();
//        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM search_log ORDER BY _id DESC",null);
//        List<SearchLogBean> list = new ArrayList<>();
//        while (cursor.moveToNext()){
//            list.add(new SearchLogBean(cursor.getString(cursor.getColumnIndex("logistics_number"))));
//        }
//        mSqliteDatabase.close();
//        return list;
//    }
//
//    public List<SearchLogBean> getSearchLog(String logisticsNumberString){
//        mSqliteDatabase = getReadableDatabase();
//        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM search_log WHERE logistics_number " +
//                        "LIKE '%"+logisticsNumberString+"%' ORDER BY _id DESC ",null);
//        List<SearchLogBean> list = new ArrayList<>();
//        while (cursor.moveToNext()){
//            list.add(new SearchLogBean(cursor.getString(cursor.getColumnIndex("logistics_number"))));
//        }
//        mSqliteDatabase.close();
//        return list;
//    }
//
//    public void insertSearchLog(String logisticsNumber){
//        mSqliteDatabase = getReadableDatabase();
//        String sql = "SELECT * FROM search_log WHERE logistics_number='"+logisticsNumber+"'";
//        Cursor cursor = mSqliteDatabase.rawQuery(sql,null);
//        if(cursor.moveToNext()){
//            mSqliteDatabase.execSQL("DELETE FROM search_log WHERE logistics_number="+logisticsNumber);
//        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("logistics_number",logisticsNumber);
//        mSqliteDatabase.insert("search_log",null,contentValues);
//        mSqliteDatabase.close();
//    }
//
//    public void clearSearchLog(){
//        mSqliteDatabase = getReadableDatabase();
//        mSqliteDatabase.execSQL("DELETE FROM search_log");
//        mSqliteDatabase.close();
//    }
//
//    public List<AddressBean> getSenderAddress(){
//        List<AddressBean> list = new ArrayList<>();
//        mSqliteDatabase = getReadableDatabase();
//        Cursor cursor = mSqliteDatabase.rawQuery("SELECT * FROM sender_address",null);
//        return list;
//    }
}
