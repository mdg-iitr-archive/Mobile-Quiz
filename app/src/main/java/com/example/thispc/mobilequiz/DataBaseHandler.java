package com.example.thispc.mobilequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    private static final String id1 = "id1";
    private static final String id2 = "id2";
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
        String z3="Create Table if not exists RandomQuestionsType(id1 Int,id2 Int,type Text)";
        db.execSQL(z3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APTITUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REASONING);
        onCreate(db);
    }
public void adRandomQuestionsType(RandomQuestionsType rqd)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(id1, rqd.getId1());
    values.put(id2, rqd.getId2());
    values.put(type, rqd.getType());
    db.insert(TABLE_RandomQuestionType, null, values);
    db.close();
}
    public void adQuestions(QuestionDetails qd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id1, qd.getId());
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
    public RandomQuestionsType getRandomQuestionsType(int id1)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from RandomQuestionsType where id1=" + id1 + "", null);
        RandomQuestionsType rqt=null;
        if (c.moveToNext() == true) {

            rqt =new RandomQuestionsType(Integer.parseInt(c.getString(0)),Integer.parseInt(c.getString(1)),c.getString(2));
        }
        return rqt;
    }
    public QuestionDetails getQuestions(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        QuestionDetails q=null;
        Cursor c = db.rawQuery("Select * from Questions where id=" + id + "", null);
        if (c.moveToNext() == true) {

            q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
        }
        return q;
    }
    public List<QuestionDetails> getAllQuestions( String type) {
        List<QuestionDetails> questionList = new ArrayList<>();
        if(type.equals("Aptitude"))
        {
            String query = "SELECT * FROM " + TABLE_APTITUDE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                   QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
                    questionList.add(q);

                } while (c.moveToNext());
            }
        }
        if(type.equals("Reasoning"))
        {
            String query = "SELECT * FROM " + TABLE_REASONING;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
                    questionList.add(q);

                } while (c.moveToNext());
            }
        }
        return questionList;
    }


}