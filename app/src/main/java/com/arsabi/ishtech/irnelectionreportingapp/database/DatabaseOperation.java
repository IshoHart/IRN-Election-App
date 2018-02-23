package com.arsabi.ishtech.irnelectionreportingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.arsabi.ishtech.irnelectionreportingapp.adapter.Vote;


/**
 * Created by Isho on 2/11/2018.
 */

public class DatabaseOperation extends SQLiteOpenHelper {

    private static String DB = "irn";
    private static int version = 1;
    private static String Create_Table = "CREATE TABLE " + Result.ResultInfo.Table + "("
            + Result.ResultInfo.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Result.ResultInfo.Name + " TEXT,"
            + Result.ResultInfo.Party + " TEXT,"
            + Result.ResultInfo.Vote + " INTEGER,"
            + Result.ResultInfo.Region + " TEXT,"
            + Result.ResultInfo.Distirct + " TEXT,"
            + Result.ResultInfo.Constituency + " INTEGER,"
            + Result.ResultInfo.Ward + " INTEGER,"
            + Result.ResultInfo.Center + " INTEGER,"
            + Result.ResultInfo.Section + " INTEGER"
            + ")";

    public DatabaseOperation(Context context) {
        super(context, DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addResult(DatabaseOperation db, Vote votes) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Result.ResultInfo.Name, votes.getName());
        values.put(Result.ResultInfo.Party, votes.getParty());
        values.put(Result.ResultInfo.Vote, votes.getVote());
        values.put(Result.ResultInfo.Region, votes.getRegion());
        values.put(Result.ResultInfo.Distirct, votes.getDistrict());
        values.put(Result.ResultInfo.Constituency, votes.getConst());
        values.put(Result.ResultInfo.Ward, votes.getCenter());
        values.put(Result.ResultInfo.Center, votes.getCenter());
        values.put(Result.ResultInfo.Section, votes.getStation());

        database.insert(Result.ResultInfo.Table, null, values);
        Log.d("Data","Added Successfully");
    }

    public Cursor getResult(DatabaseOperation db) {
        SQLiteDatabase database = db.getReadableDatabase();
        String Column[] = {
                Result.ResultInfo.Name,
                Result.ResultInfo.Party,
                Result.ResultInfo.Vote,
                Result.ResultInfo.Region,
                Result.ResultInfo.Distirct,
                Result.ResultInfo.Constituency,
                Result.ResultInfo.Ward,
                Result.ResultInfo.Center,
                Result.ResultInfo.Section
        };
        Cursor cursor = database.query(Result.ResultInfo.Table,Column,null,null,null,null,null);
        return cursor;
    }

    public void removeAllResult(DatabaseOperation db){
        SQLiteDatabase database = db.getWritableDatabase();
        String where = Result.ResultInfo.ID + " > 0";
        database.delete(Result.ResultInfo.Table,where, new String[]{"1"});
    }
}
