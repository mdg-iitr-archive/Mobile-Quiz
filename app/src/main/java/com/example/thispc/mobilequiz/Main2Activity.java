package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    DataBaseHandler dbh;
    public static int c=1;
public void twodevices(View v)
{
    Intent i=new Intent(Main2Activity.this,Twodevices.class);
   // i.putExtra("type","1");
    startActivity(i);
}
    public void multipledevices(View v)
    {
        AlertDialog.Builder aa=new AlertDialog.Builder(Main2Activity.this);
        aa.setTitle("Join As?");
        aa.setMessage("Joining As Server Would Allow You To Select Questions");
        aa.setPositiveButton("Server", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Main2Activity.this, CategoryForServer.class);
                startActivity(i);
            }
        });
        aa.setNegativeButton("Client", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Main2Activity.this,Cleint.class);
                startActivity(i);
            }
        });

                aa.show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbh = new DataBaseHandler(this);
        dbh.clear();
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
