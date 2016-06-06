package com.example.thispc.mobilequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by this pc on 07-06-2016.
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ListViewHolder>{

    List<QuestionDetails> questionList;
    Activity parentAct;

    public QuestionListAdapter(List<QuestionDetails> questionList, Activity activity){
        this.questionList = questionList;
        parentAct = activity;
    }

    @Override
    public QuestionListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.question_card, parent, false);

        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (QuestionListAdapter.ListViewHolder holder, int position) {
        QuestionDetails qd =questionList.get(position);
        holder.ques.setText(qd.getQuestion().toString());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView ques;
        protected CheckBox checkbox;


        public ListViewHolder(View vi) {
            super(vi);
            ques = (TextView) vi.findViewById(R.id.qtag);
            checkbox=(CheckBox)vi.findViewById(R.id.checkbox);
            vi.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
