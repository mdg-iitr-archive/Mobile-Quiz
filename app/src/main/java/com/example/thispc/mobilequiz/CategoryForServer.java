package com.example.thispc.mobilequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoryForServer extends AppCompatActivity {
    SharedPreferences.Editor editor=null;

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
        Intent i =new Intent(CategoryForServer.this,Server.class);
        startActivity(i);

    }

}

