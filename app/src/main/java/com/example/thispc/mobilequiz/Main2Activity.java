package com.example.thispc.mobilequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    DataBaseHandler dbh;
public void twodevices(View v)
{
    Intent i=new Intent(Main2Activity.this,Twodevices.class);
    i.putExtra("type","1");
    startActivity(i);
}
    public void multipledevices(View v)
    {
        Intent i=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbh = new DataBaseHandler(this);
        QuestionDetails qd = new QuestionDetails(1,"name of country","india","malaysia","usa","germany","india" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(2,"name of country2","germany","malaysia","usa","india","india" );
        dbh.adReasoningQ(qd);

        qd = new QuestionDetails(1,"name of country","india","malaysia","usa","germany","india" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(2,"name of country2","germany","malaysia","usa","india","india" );
        dbh.adAptitudeQ(qd);
    }
}
