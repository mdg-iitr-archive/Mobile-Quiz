package com.example.thispc.mobilequiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class QuestionList extends AppCompatActivity {
    RecyclerView recList;
    DataBaseHandler dbh;
    public static int c=0;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        dbh = new DataBaseHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<QuestionDetails> stList = ((QuestionListAdapter) mAdapter).getQuestionList();


                for (int i = 0; i < stList.size(); i++) {
                    QuestionDetails qd = stList.get(i);
                    if (qd.isSelected() == true) {
                              RandomQuestionsType rqt= new RandomQuestionsType(c,qd.getId(),"Aptitude");
                         dbh.adRandomQuestionsType(rqt);
                           c++;
                    }

                }


            }
        });
        recList = (RecyclerView) findViewById(R.id.questionList_recycler);
        dbh = new DataBaseHandler(this);
        addToList();
    }

    private List<QuestionDetails> createList() {
        List<QuestionDetails> result;
        result=dbh.getAllQuestions("Aptitude");
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

}

