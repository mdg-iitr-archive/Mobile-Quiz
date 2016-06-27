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
    private static final String TABLE_VERBAL = "Verbal";
    private static final String TABLE_ENGG = "Engg";
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
    private static final String qTag = "qTag";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String z="Create Table if not exists Reasoning(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text,qTag Text)";
        db.execSQL(z);
        String z1="Create Table if not exists Aptitude(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text,qTag Text)";
        db.execSQL(z1);
        String z3="Create Table if not exists Verbal(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text,qTag Text)";
        db.execSQL(z3);
        String z5="Create Table if not exists Engg(id Int,question Text,option1 Text,option2 Text,option3 Text,option4 Text,answer Text,qTag Text)";
        db.execSQL(z5);
        String z4="Create Table if not exists RandomQuestionsType(id1 Int,id2 Int,type Text)";
        db.execSQL(z4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APTITUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REASONING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RandomQuestionType);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERBAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGG);
        onCreate(db);
    }
    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_REASONING+";");
        db.execSQL("DELETE FROM " + TABLE_APTITUDE + ";");
        db.execSQL("DELETE FROM " + TABLE_VERBAL + ";");
        db.execSQL("DELETE FROM " + TABLE_RandomQuestionType + ";");
        db.execSQL("DELETE FROM " + TABLE_ENGG + ";");
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
    values.put(qTag, qd.getQtag());

    db.insert(TABLE_REASONING, null, values);
    db.close();
}
    public void adVerbalQ(QuestionDetails qd)
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
        values.put(qTag, qd.getQtag());

        db.insert(TABLE_VERBAL, null, values);
        db.close();
    }
    public void adEnggQ(QuestionDetails qd)
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
        values.put(qTag, qd.getQtag());

        db.insert(TABLE_ENGG, null, values);
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
        values.put(qTag, qd.getQtag());

        db.insert(TABLE_APTITUDE, null, values);
        db.close();
    }
    public  QuestionDetails getAptitudeQ(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Aptitude where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

         qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
        }
        return qd;
    }
    public  QuestionDetails getEnggQ(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Aptitude where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

            qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
        }
        return qd;
    }
    public  QuestionDetails getReasoningQ(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Reasoning where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

            qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
        }
        return qd;
    }
    public  QuestionDetails getVerbalQ(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from Verbal where id=" + id + "", null);
        QuestionDetails qd=null;
        if (c.moveToNext() == true) {

            qd =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7));
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
    public List<QuestionDetails> getAllQuestions( String type) {
        List<QuestionDetails> questionList = new ArrayList<>();
        if(type.equals("Aptitude"))
        {
            String query = "SELECT * FROM " + TABLE_APTITUDE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                   QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),false,c.getString(7));
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
                    QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),false,c.getString(7));
                    questionList.add(q);

                } while (c.moveToNext());
            }
        }
        if(type.equals("Verbal"))
        {
            String query = "SELECT * FROM " + TABLE_VERBAL;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),false,c.getString(7));
                    questionList.add(q);

                } while (c.moveToNext());
            }
        }
        if(type.equals("Engg"))
        {
            String query = "SELECT * FROM " + TABLE_ENGG;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    QuestionDetails q =new QuestionDetails(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),false,c.getString(7));
                    questionList.add(q);

                } while (c.moveToNext());
            }
        }
        return questionList;
    }
}