package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    DataBaseHandler dbh;
    Button b1;
    public static int c=1;
    public static String type;
    public static String joined_as;
public void twodevices(View v)
{
    type ="twodevices";
    AlertDialog.Builder aa=new AlertDialog.Builder(Main2Activity.this);
    aa.setTitle("Join As?");
    aa.setMessage("Joining As Server Would Allow You To Select Questions and you would also be a part of quiz");
    aa.setPositiveButton("Server", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            joined_as="server";
            Intent i = new Intent(Main2Activity.this, CategoryForServer.class);
            startActivity(i);
        }
    });
    aa.setNegativeButton("Client", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            joined_as="client";
            Intent i = new Intent(Main2Activity.this,Twodevices.class);
            startActivity(i);
        }
    });

    aa.show();
    //Intent i=new Intent(Main2Activity.this,Twodevices.class);
   // i.putExtra("type","1");
   // startActivity(i);
}
    public void multipledevices(View v)
    {
        type="multidevices";
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
        b1=(Button)findViewById(R.id.button3);
        Typeface m=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m);
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
