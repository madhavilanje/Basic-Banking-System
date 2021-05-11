package com.example.rupaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9623088065,'Arijit',1250.00,'arijit.30@gmail.com','XXXXXXXXXXXX6237','MNO09877586')");
        db.execSQL("insert into user_table values(7625896561,'Diksha',8550.00,'diksha.9623@gmail.com','XXXXXXXXXXXX9342','GHI98768666')");
        db.execSQL("insert into user_table values(7588880352,'Madhavi Lanje',9200.00,'madhavi.25@gmail.com','XXXXXXXXXXXX7416','PQR87657775')");
        db.execSQL("insert into user_table values(8857835398,'Tanvi',1500.00,'tanvi.14@gmail.com','XXXXXXXXXXXX3124','XYZ76534544')");
        db.execSQL("insert into user_table values(123456785,'Supriya',2900.00,'supriya.21@gmail.com','XXXXXXXXXXXX1348','GHI65487774')");
        db.execSQL("insert into user_table values(9876543219,'Dimpal',3500.00,'dimpal.04@gmail.com','XXXXXXXXXXXX9459','MNO5431249')");
        db.execSQL("insert into user_table values(8956231472,'Swarali',4050.00,'swarali.28@gmail.com','XXXXXXXXXXXX8524','JKL43236614')");
        db.execSQL("insert into user_table values(8529631472,'Viraj',6500.00,'viraj.96@gmail.com','XXXXXXXXXXXX9238','DEF32105699')");
        db.execSQL("insert into user_table values(7569841235,'Pranali',2090.00,'pranali.25@gmail.com','XXXXXXXXXXXX1453','JKL2104224966')");
        db.execSQL("insert into user_table values(9632145871,'Kunal',2050.00,'kunal.30@gmail.com','XXXXXXXXXXXX9561','XYZ109877870')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
