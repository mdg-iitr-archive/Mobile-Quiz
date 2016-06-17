package com.example.thispc.mobilequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CategoryForServer extends AppCompatActivity {
    SharedPreferences.Editor editor=null;
    public static String Duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_for_server);
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
    public void ok(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(CategoryForServer.this);
        edittext.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        alert.setMessage("Duration Should Be In Minutes");
        alert.setTitle("Enter Duration");

        alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Duration = edittext.getText().toString();
                if(Duration!=null)
                {
                    Intent i=new Intent(CategoryForServer.this,Server.class);
                    startActivity(i);

                }
                if(Duration==null)
                {
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

