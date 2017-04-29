package com.kartik.barcode;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kartik on 7/4/2016.
 */
public class databasehelper extends SQLiteOpenHelper
{

    final String TABLE_NAME="users";

    public databasehelper(Context context)
    {
        super(context, "newdb", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table "+TABLE_NAME+"(userid String PRIMARY KEY,password text,age int,gender text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("drop table "+TABLE_NAME);
        onCreate(db);

    }

    Cursor getdata()
    {
        SQLiteDatabase db= getReadableDatabase();

        return db.query(TABLE_NAME,null,null,null,null,null,null);
    }

    void  putdata(String name,String password,int age,String gen)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("userid",name);
        cv.put("password",password);
        cv.put("age",age);
        cv.put("gender",gen);

        db.insert(TABLE_NAME,null,cv);

        db.close();
    }

    void deleteRecord(String ColumnName,String data)
    {
        SQLiteDatabase db= getWritableDatabase();
        String[] arr=new String[]{data};
        db.delete(TABLE_NAME, ColumnName+"=?",arr);
        db.close();
    }

    public String logins(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select userid, password from "+TABLE_NAME;
        Cursor c=db.rawQuery(query,null);
        String a,b;
        b="Not Found";
        if(c.moveToFirst())
        {
            do
            {
                a=c.getString(0);
                if(a.equals(username))
                {
                    b=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        return b;
    }
}