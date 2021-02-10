package com.example.readandwritedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper  extends SQLiteOpenHelper {

        private  static  final  String DATABASE_NAME="Student.db";
        private  static  final  String TABLE_NAME="student_details";
        private  static  final  String ID="_id";
        private  static  final  String NAME="Name";
        private  static  final  String ROLL="Roll";
        private  static  final  String AGE="Age";
        private  static  final  String GENDER="Gender";
        private  static  final  int VERSION_NUMBER=4;
        private  static  final  String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+NAME+" VARCHAR(2550),"+ROLL+" VARCHAR(50), "+AGE+" INTEGER,"+GENDER+" VARCHAR(50));";
        private  static  final  String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        private  static  final  String GETVALUE="SELECT * FROM "+TABLE_NAME;
        private  Context context;



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
            try{
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (Exception e){
             Toast.makeText(context, "Error : "+e, Toast.LENGTH_SHORT).show();
            }
 
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
        Toast.makeText(context, "Something Wrong"+e, Toast.LENGTH_SHORT).show();
        }

    }


    public long insertData(String name,String roll,String age,String gender){

       SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(ROLL,roll);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);

      long rowid=  sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
return rowid;



    }

    public Cursor displayalldata(){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(GETVALUE,null);
        return cursor;

    }



}
