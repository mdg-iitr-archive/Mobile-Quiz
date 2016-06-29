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
        QuestionDetails qd =  new QuestionDetails(1,"Look at this series: F2, __, D8, C16, B32, ... What number should fill the blank?","A16","G4","E4","E3","E4","series" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(2,"Look at this series: 8, 43, 11, 41, __, 39, 17, ... What number should fill in the blank?","8","14","43","44","14","fill blank" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(3,"Blueberries cost more than strawberries.Blueberries cost less than raspberries.Raspberries cost more than strawberries and blueberries.If the first two statements are true, the third statement is","True","False","Uncertain","Unknown","True","correct statement" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(4,"Apartments in the Riverdale Manor cost less than apartments in The Gaslight Commons.Apartments in the Livingston Gate cost more than apartments in the The Gaslight Commons.Of the three apartment buildings, the Livingston Gate costs the most.If the first two statements are true, the third statement is","True","False","Uncertain","Unknown","True","Aparments" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(5,"QAR, RAS, SAT, TAU, _____","UAV","UAT","TAS","TAT","UAV","Letter Series" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(6, "Pointing to Varman, Madhav said, \"I am the only son of one of the sons of his father.\" How is Varman related to Madhav?","Nephew","Uncle","Father","Mother","Uncle","Varma And Madhav");
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(7, "Introducing a woman, Shashank said, \"She is the mother of the only daughter of my son.\" How that woman is related to Shashank?","Daughter","Sister","Wife","Daughter-in-law","Daughter-in-law","Shashak's Relation");
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(8,"A flagstaff 17.5 m high casts a shadow of length 40.25 m. The height of the building, which casts a shadow of length 28.75 m under similar conditions will be:","10m","12.5m","17.5m","21.25m","12.5m","casting shadow");
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(9, "An industrial loom weaves 0.128 metres of cloth every second. Approximately, how many seconds will it take for the loom to weave 25 metres of cloth?","178","195","204","488","195","weaving loom" );
        dbh.adReasoningQ(qd);
        qd = new QuestionDetails(10, "The banker's discount on a sum of money for 1 years is Rs. 558 and the true discount on the same sum for 2 years is Rs. 600. The rate percent is:","10%","13%","12%","15%","12%","Banker's discount" );
        dbh.adReasoningQ(qd);


         qd = new QuestionDetails(1,"A flagstaff 17.5 m high casts a shadow of length 40.25 m. The height of the building, which casts a shadow of length 28.75 m under similar conditions will be:","10m","12.5m","17.5m","21.25m","12.5m","casting shadow");
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(2, "An industrial loom weaves 0.128 metres of cloth every second. Approximately, how many seconds will it take for the loom to weave 25 metres of cloth?","178","195","204","488","195","weaving loom" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(3, "How many 4-letter words with or without meaning, can be formed out of the letters of the word,'LOGARITHMS', if repetition of letters is not allowed? ","40","400","5040","2520","5040","4 letter words" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(4, "One card is drawn at random from a pack of 52 cards. What is the probability that the card drawn is a face card (Jack, Queen and King only)?","1/13","3/13","1/4","9/52","3/13","probability" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(5, "The banker's discount on a sum of money for 1 years is Rs. 558 and the true discount on the same sum for 2 years is Rs. 600. The rate percent is:","10%","13%","12%","15%","12%","Banker's discount" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(6, "P is the mother of K; K is the sister of D; D is the father of J. How is P related to J?","Mother","Grandmother","Aunt","Son","Uncle","P & K");
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(7, "Pointing to Varman, Madhav said, \"I am the only son of one of the sons of his father.\" How is Varman related to Madhav?","Nephew","Uncle","Father","Mother","Uncle","Varma And Madhav");
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(8,"Apartments in the Riverdale Manor cost less than apartments in The Gaslight Commons.Apartments in the Livingston Gate cost more than apartments in the The Gaslight Commons.Of the three apartment buildings, the Livingston Gate costs the most.If the first two statements are true, the third statement is","True","False","Uncertain","Unknown","True","Aparments" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(9,"Blueberries cost more than strawberries.Blueberries cost less than raspberries.Raspberries cost more than strawberries and blueberries.If the first two statements are true, the third statement is","True","False","Uncertain","Unknown","True","correct statement" );
        dbh.adAptitudeQ(qd);
        qd = new QuestionDetails(10, "Select Synonym for EMBEZZLE","Misappropriate","Balance","Remunerate","Clear","Misappropriate","Select synonym");
        dbh.adAptitudeQ(qd);

        qd = new QuestionDetails(1, "A school boy who cuts classes frequently is a","Martinet","Defeatist","Sycophant","Truant","Truant","one word subtitle");
        dbh.adVerbalQ(qd);
        qd = new QuestionDetails(2, "Fate smiles ...... those who untiringly grapple with stark realities of life.","with","over","on","round","on","Select word");
        dbh.adVerbalQ(qd);
        qd = new QuestionDetails(3, "Select Synonym for EMBEZZLE","Misappropriate","Balance","Remunerate","Clear","Misappropriate","Select synonym");
        dbh.adVerbalQ(qd);
        qd = new QuestionDetails(4, "When he\n" +
                "P :\tdid not know\n" +
                "Q :\the was nervous and\n" +
                "R :\theard the hue and cry at midnight\n" +
                "S :\twhat to do\n" +
                "The Proper sequence should be:","RQPS","QSPR","SQPR","PQRS","RQPS","ordering of words");
                dbh.adVerbalQ(qd);
        qd = new QuestionDetails(5, "A school boy who cuts classes frequently is a","Martinet","Defeatist","Sycophant","Truant","Truant","one word subtitle");
        dbh.adVerbalQ(qd);
        qd = new QuestionDetails(6, "Find antonym for EXODUS","Influx","Home-coming","Return","Restoration","Influx","Select antonym");
        dbh.adVerbalQ(qd);

        qd = new QuestionDetails(1, "Amit said - \"This girl is the wife of the grandson of my mother\". How is Amit related to the girl?","Brother","Grandfather","Husband","Father-in-law","Father-in-law","Amit's Relation");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(2, "Pointing towards a man, a woman said, \"His mother is the only daughter of my mother.\" How is the woman related to the man?","Mother","Grandmother","Sister","Daughter","Mother","Relation Of Woman");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(3, "Introducing Sonia, Aamir says, \"She is the wife of only nephew of only brother of my mother.\" How Sonia is related to Aamir?","Wife","Sister in law"," More data","Sister","Wife","Sonia And Amir");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(4, " Introducing a man, a woman said, \"He is the only son of the mother of my mother.\" How is the woman related to the man?","Mother","Sister","Niece","Maternal Aunt","Niece","Son of Mother");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(5,  "Pointing to a photograph Anjali said, \"He is the son of the only son of my grandfather.\" How is the man in the photograph related to Anjali?","Brother","Uncle","Son","More   Data","Brother","Angali and grandfather");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(6, "Pointing to a person, Deepak said, \"His only brother is the father of my daughter's father\". How is the person related to Deepak?","Father","Grandfather","Uncle","Son","Uncle","Depak's Relation");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(7, "P is the mother of K; K is the sister of D; D is the father of J. How is P related to J?","Mother","Grandmother","Aunt","Son","Uncle","P & K");
        dbh.adEnggQ(qd);
        qd = new QuestionDetails(8, "Pointing to Varman, Madhav said, \"I am the only son of one of the sons of his father.\" How is Varman related to Madhav?","Nephew","Uncle","Father","Mother","Father","Varma And Madhav");
        dbh.adEnggQ(qd);

    }
}
