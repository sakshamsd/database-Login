package com.example.admin.databaselogin.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 6/7/2017.
 */

public class dbhelper extends SQLiteOpenHelper {

    final static String name = "students";
    final static int version = 1;

    String createTable = "CREATE TABLE if not exists `student_details` (\n" +
            "\t`id`\tTEXT,\n" +
            "\t`name`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\tPRIMARY KEY(id)\n" +
            ")";

    public dbhelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTable);
    }

   /* public void insertStudent(ContentValues cv){
        getWritableDatabase().insert("student_details","",cv);
        Log.d("entry", cv.toString());

    }*/

    public long insertStudent(student_test studentTest){
        ContentValues cv = new ContentValues();
        cv.put("id", studentTest.getId());
        cv.put("name", studentTest.getName());
        cv.put("address",studentTest.getAddress());


       long id = getWritableDatabase().insert("student_details","",cv);
        Log.d("entry", cv.toString());
        return id;

    }

    /*public ArrayList<student_info> getStudentInfo(){
        String sql = "select * from student_details";

        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        ArrayList<student_info> list = new ArrayList<student_info>();

        while(cursor.moveToNext()){
            student_info info = new student_info();

            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.name = cursor.getString(cursor.getColumnIndex("name"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));

            list.add(info);

            Log.d("data", info.getName());


        }
        cursor.close();
        return list;

    }
*/



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
