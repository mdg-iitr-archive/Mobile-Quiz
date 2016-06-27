package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryForServer extends AppCompatActivity {
    SharedPreferences.Editor editor=null;
    public static String Duration;
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_for_server);
        b1=(Button)findViewById(R.id.button8);
        Typeface m=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m);
        b1=(Button)findViewById(R.id.button6);
        Typeface m2=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m2);
        b1=(Button)findViewById(R.id.button9);
        Typeface m3=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m3);
        b1=(Button)findViewById(R.id.button10);
        Typeface m4=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m4);
        b1=(Button)findViewById(R.id.button5);
        Typeface m5=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        b1.setTypeface(m5);
        t1=(TextView)findViewById(R.id.textView2);
        Typeface m1=Typeface.createFromAsset(getAssets(),"MING____.ttf");
        t1.setTypeface(m1);
        SharedPreferences prefs = getSharedPreferences("category", MODE_PRIVATE);
        editor = prefs.edit();
    }
    public void aptitude(View v) {

        editor.putString("category", "Aptitude");
        editor.commit();
        Intent i=new Intent(CategoryForServer.this,QuestionList.class);
        startActivity(i);
    }

    public void reasoning(View v) {
        editor.putString("category", "Reasoning");
        editor.commit();
        Intent i=new Intent(CategoryForServer.this,QuestionList.class);
        startActivity(i);
    }
    public void verbal(View v) {
        editor.putString("category", "Verbal");
        editor.commit();
        Intent i=new Intent(CategoryForServer.this,QuestionList.class);
        startActivity(i);
    }
    public void engg(View v) {
        editor.putString("category", "Engg");
        editor.commit();
        Intent i = new Intent(CategoryForServer.this, QuestionList.class);
        startActivity(i);
    }
    public void ok(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(CategoryForServer.this);
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setMessage("Duration Should Be In Minutes");
        alert.setTitle("Enter Duration");

        alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Duration = edittext.getText().toString();
                if (Duration != null) {
                    if (Duration.length() == 1) {
                        Duration = "0" + Duration;
                    }
                    if (Main2Activity.type =="multidevices") {
                        Intent i = new Intent(CategoryForServer.this, Server.class);
                        startActivity(i);
                    }
                    if (Main2Activity.type == "twodevices") {
                        Intent i = new Intent(CategoryForServer.this,TwodeviceServer.class);
                        startActivity(i);

                    }


                }
                if (Duration == null) {
                    Toast.makeText(getApplicationContext(), "Please Enter Duration", Toast.LENGTH_SHORT).show();

                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
       /* Intent i =new Intent(CategoryForServer.this,Server.class);
        startActivity(i);*/

    }

}

