package com.example.thispc.mobilequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
public void twodevices(View v)
{
    Intent i=new Intent(Main2Activity.this,Twodevices.class);
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
    }
}
