package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    DataBaseHandler dbh;
    Button b1;
    TextView t1;
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
        b1=(Button)findViewById(R.id.button4);
        Typeface m1=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m1);
        t1=(TextView)findViewById(R.id.textView5);
        Typeface m2=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        t1.setTypeface(m2);
        dbh = new DataBaseHandler(this);
        dbh.clear();
        QuestionDetails qd =  new QuestionDetails(1,"name of country","india","malaysia","usa","germany","india","country" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(2,"name of country2","germany","malaysia","usa","india","india","country" );
        dbh.adReasoningQ(qd);

         qd = new QuestionDetails(1,"A flagstaff 17.5 m high casts a shadow of length 40.25 m. The height of the building, which casts a shadow of length 28.75 m under similar conditions will be:","10m","12.5m","17,5m","21.25m","12.5m","casting shadow");
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(2, "An industrial loom weaves 0.128 metres of cloth every second. Approximately, how many seconds will it take for the loom to weave 25 metres of cloth?","178","195","204","488","195","weaving loom" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(3, "How many 4-letter words with or without meaning, can be formed out of the letters of the word,'LOGARITHMS', if repetition of letters is not allowed? ","40","400","5040","2520","5040","4 letter words" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(4, "One card is drawn at random from a pack of 52 cards. What is the probability that the card drawn is a face card (Jack, Queen and King only)?","1/13","3/13","1/4","9/52","3/13","probability" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(5, "The banker's discount on a sum of money for 1 years is Rs. 558 and the true discount on the same sum for 2 years is Rs. 600. The rate percent is:","10%","13%","12%","15%","12%","Banker's discount" );
        dbh.adAptitudeQ(qd);
    }
}
