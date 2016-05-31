package com.example.thispc.mobilequiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by this pc on 31-05-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Questions";
    private static final String TABLE_APTITUDE = "Aptitude";
    private static final String TABLE_REASONING = "Reasoning";

    private static final String id = "id";
    private static final String question = "question";
    private static final String option1 = "option1";
    private static final String option2 = "option2";
    private static final String option3 = "option3";
    private static final String option4 = "option4";
    private static final String answer = "answer";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_reasoning = "CREATE TABLE " + TABLE_REASONING + "(" + id + " INTEGER PRIMARY KEY," + question + " TEXT," + option1 + " TEXT," + option2 + " TEXT," + option3 + " TEXT" + option4 + "TEXT" + answer + "TEXT)";
        db.execSQL(create_table_reasoning);
        String create_table_aptitude = "CREATE TABLE " + TABLE_APTITUDE + "(" + id + " INTEGER PRIMARY KEY," + question + " TEXT," + option1 + " TEXT," + option2 + " TEXT," + option3 + " TEXT" + option4 + "TEXT" + answer + "TEXT)";
        db.execSQL(create_table_aptitude);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APTITUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REASONING);
        onCreate(db);
    }

public void adReasoningQ(QuestionDetails q)
{

}
}