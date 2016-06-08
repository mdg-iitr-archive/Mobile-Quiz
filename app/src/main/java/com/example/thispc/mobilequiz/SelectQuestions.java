package com.example.thispc.mobilequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SelectQuestions extends AppCompatActivity {
    DataBaseHandler dbh;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);
        dbh = new DataBaseHandler(this);
        for (int i=1;i<=Cleint.qnumber;i++)
        {
            RandomQuestionsType rqt= dbh.getRandomQuestionsType(i);
            Toast.makeText(getApplicationContext(),rqt.getType().toString().equals("Aptitude")?dbh.getAptitudeQ(rqt.getId2()).getQuestion():dbh.getReasoningQ(rqt.getId2()).getQuestion(),Toast.LENGTH_LONG).show();


         /*  RandomQuestionsType rqt= dbh.getRandomQuestionsType(i);
            if(rqt.getType().toString().equals("Aptitude"))
            {
              //  dbh.adQuestions(dbh.getAptitudeQ(rqt.getId2()));
            }
            if(rqt.getType().toString().equals("Reasoning"))
            {
               // dbh.adQuestions(dbh.getReasoningQ(rqt.getId2()));
            }*/
        }


    }
}
