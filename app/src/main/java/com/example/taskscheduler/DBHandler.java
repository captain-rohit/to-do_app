package com.example.taskscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Tasks.db";
    private static final String TABLE_TASK = "Tasks";
    private static final String COLOUMN_ID = "_id";
    private static final String COLOUMN_TASKNAME = "_name";
    private static final String COLOUMN_DONE = "_done";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_TASK+"("+COLOUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLOUMN_TASKNAME+ " TEXT UNIQUE,"+COLOUMN_DONE+" INTEGER"+" );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }
    public void addTask(models TaskModel){
        ContentValues values = new ContentValues();
        values.put(COLOUMN_TASKNAME,TaskModel.get_name());
        values.put(COLOUMN_DONE,TaskModel.get_done());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASK,null,values);
        db.close();
    }
    public void deleteTask(String Taskname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_TASK+" WHERE "+ COLOUMN_TASKNAME+"=\""+Taskname+"\";");
    }
    public ArrayList<HashMap<String, String>> viewDB(){
        ArrayList<HashMap<String, String>> all_tasks = new ArrayList<>();
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT "+ COLOUMN_TASKNAME + " FROM "+TABLE_TASK+" WHERE 1";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            HashMap<String,String> user = new HashMap<>();
            if(c.getString(c.getColumnIndex(COLOUMN_TASKNAME))!=null){
                user.put("name",c.getString(c.getColumnIndex(COLOUMN_TASKNAME)));
                all_tasks.add(user);
            }
            c.moveToNext();
        }
        return all_tasks;
    }
}
