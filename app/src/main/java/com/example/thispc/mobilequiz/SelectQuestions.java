package com.example.thispc.mobilequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectQuestions extends AppCompatActivity {
    DataBaseHandler dbh;
    public static int c=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);
        dbh = new DataBaseHandler(this);
        for (int i=0;i<=10;i++)
        {
           RandomQuestionsType rqt= dbh.getRandomQuestionsType(i);
            if(rqt.getType().toString().equals("Aptitude"))
            {
                dbh.adQuestions(dbh.getAptitudeQ(rqt.getId2()));
            }
            if(rqt.getType().toString().equals("Reasoning"))
            {
                dbh.adQuestions(dbh.getReasoningQ(rqt.getId2()));
            }
        }


    }
}
