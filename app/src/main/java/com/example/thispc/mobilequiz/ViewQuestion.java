package com.example.thispc.mobilequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewQuestion extends AppCompatActivity {
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);
        t1=(TextView)findViewById(R.id.textView4);
        t1.setText(getIntent().getExtras().getString("question"));
    }
}
