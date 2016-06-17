package com.example.thispc.mobilequiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionList extends AppCompatActivity {
    RecyclerView recList;
    DataBaseHandler dbh;
    String category=null;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        dbh = new DataBaseHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = getSharedPreferences("category", MODE_PRIVATE);
       category= prefs.getString("category",null);
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        recList = (RecyclerView) findViewById(R.id.questionList_recycler);
        dbh = new DataBaseHandler(this);
        addToList();
    }
    public void ok(View v) {

        List<QuestionDetails> stList = QuestionListAdapter.questionList;
        Toast.makeText(getApplicationContext(), stList.size()+" ", Toast.LENGTH_SHORT).show();
      for (int i = 0; i <stList.size(); i++) {
            QuestionDetails qd = stList.get(i);
               if (qd.isSelected()) {
                RandomQuestionsType rqt = new RandomQuestionsType(Main2Activity.c, qd.getId(), category);
                dbh.adRandomQuestionsType(rqt);
                Toast.makeText(getApplicationContext(), "saved in database" + Main2Activity.c, Toast.LENGTH_SHORT).show();
                ++Main2Activity.c;
            }
        }
    }


    private List<QuestionDetails> createList() {
        List<QuestionDetails> result;
        result=dbh.getAllQuestions(category);
        return result;
    }
    public void addToList(){
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        QuestionListAdapter ca = new QuestionListAdapter(createList(),this);
        recList.setAdapter(ca);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.selectall) {


        }

        return super.onOptionsItemSelected(item);
    }

}

