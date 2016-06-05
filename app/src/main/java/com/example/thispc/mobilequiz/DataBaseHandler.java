package com.example.thispc.mobilequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by this pc on 31-05-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Questions";
    private static final String TABLE_APTITUDE = "Aptitude";
    private static final String TABLE_REASONING = "Reasoning";
    private static final String TABLE_RandomQuestionType = "RandomQuestionsType";
    private static final String TABLE_RandomQuestion = "RandomQuestions";

    private static final String id = "id";
    private static final String question = "question";
    private static final String option1 = "option1";
    private static final String option2 = "option2";
    private static final String option3 = "option3";
    private static final String option4 = "option4";
    private static final String answer = "answer";
    private static final String type = "type";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String z="Create Table if not exists Reasoning(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text)";
        db.execSQL(z);
        String z1="Create Table if not exists Aptitude(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text)";
        db.execSQL(z1);
        String z2="Create Table if not exists RandomQuestions(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text)";
        db.execSQL(z2);
        String z3="Create Table if not exists RandomQuestionsType(id Int,type Text)";
        db.execSQL(z3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APTITUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REASONING);
        onCreate(db);
    }
public void setRandomQuestionsType(RandomQuestionsType rqd)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(id, rqd.getId());
    values.put(type, rqd.getType());
    db.insert(TABLE_RandomQuestionType, null, values);
    db.close();
}
    public void adQuestions(QuestionDetails qd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, qd.getId());
        values.put(question, qd.getQuestion());
        values.put(option1, qd.getOption1());
        values.put(option2, qd.getOption2());
        values.put(option3, qd.getOption3());
        values.put(option4, qd.getOption4());
        values.put(answer, qd.getAnswer());

        db.insert(TABLE_RandomQuestion, null, values);
        db.close();
    }
public void adReasoningQ(QuestionDetails qd)
{
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(id, qd.getId());
    values.put(question, qd.getQuestion());
    values.put(option1, qd.getOption1());
    values.put(option2, qd.getOption2());
    values.put(option3, qd.getOption3());
    values.put(option4, qd.getOption4());
    values.put(answer, qd.getAnswer());

    db.insert(TABLE_REASONING, null, values);
    db.close();
}
    public void adAptitudeQ(QuestionDetails qd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, qd.getId());
        values.put(question, qd.getQuestion());
        values.put(option1, qd.getOption1());
        values.put(option2, qd.getOption2());
        values.put(option3, qd.getOption3());
        values.put(option4, qd.getOption4());
        values.put(answer, qd.getAnswer());

        db.insert(TABLE_APTITUDE, null, values);
        db.close();
    }
    public  QuestionDetails getAptitudeQ(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Aptitude where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

         qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
        }
        return qd;
    }
    public  QuestionDetails getReasoningQ(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Reasoning where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

            qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
        }
        return qd;
    }
}