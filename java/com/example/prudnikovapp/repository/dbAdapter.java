package com.example.prudnikovapp.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.prudnikovapp.models.ActivityEntity;

import java.util.ArrayList;

public class dbAdapter {
    private Repository dbHelper;
    private SQLiteDatabase database;

    public dbAdapter(Context context){
        dbHelper = new Repository(context.getApplicationContext());
    }

    public dbAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {Repository.COLUMN_ID, Repository.COLUMN_NAME, Repository.COLUMN_TIME, Repository.COLUMN_MAXTIME};
        return  database.query(Repository.TABLE, columns, null, null, null, null, null);
    }

    public ArrayList<ActivityEntity> getActivity(){
        ArrayList<ActivityEntity> activityEntities = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Repository.COLUMN_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Repository.COLUMN_NAME));
            @SuppressLint("Range") int time = cursor.getInt(cursor.getColumnIndex(Repository.COLUMN_TIME));
            @SuppressLint("Range") int maxTime = cursor.getInt(cursor.getColumnIndex(Repository.COLUMN_MAXTIME));
            activityEntities.add(new ActivityEntity(id, name, time, maxTime));
        }
        cursor.close();
        return  activityEntities;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, Repository.TABLE);
    }

    public ActivityEntity getUser(int id){
        ActivityEntity activityEntity = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", Repository.TABLE, Repository.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Repository.COLUMN_NAME));
            @SuppressLint("Range") int time = cursor.getInt(cursor.getColumnIndex(Repository.COLUMN_TIME));
            @SuppressLint("Range") int maxTime = cursor.getInt(cursor.getColumnIndex(Repository.COLUMN_MAXTIME));
            activityEntity = new ActivityEntity(id, name, time, maxTime);
        }
        cursor.close();
        return  activityEntity;
    }

    public long insert(ActivityEntity activityEntity){

        ContentValues cv = new ContentValues();
        cv.put(Repository.COLUMN_NAME, activityEntity.getName());
        cv.put(Repository.COLUMN_TIME, activityEntity.getTime());
        cv.put(Repository.COLUMN_MAXTIME, activityEntity.getMaxTime());

        return  database.insert(Repository.TABLE, null, cv);
    }

    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(Repository.TABLE, whereClause, whereArgs);
    }

    public long update(ActivityEntity activityEntity){

        String whereClause = Repository.COLUMN_ID + "=" + String.valueOf(activityEntity.getCount());
        ContentValues cv = new ContentValues();
        cv.put(Repository.COLUMN_NAME, activityEntity.getName());
        cv.put(Repository.COLUMN_TIME, activityEntity.getTime());
        cv.put(Repository.COLUMN_MAXTIME, activityEntity.getMaxTime());
        return database.update(Repository.TABLE, cv, whereClause, null);
    }
}
