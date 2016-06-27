package com.example.thispc.mobilequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this pc on 07-06-2016.
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ListViewHolder>{

   public static List<QuestionDetails> questionList=new ArrayList<>();
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
    public void onBindViewHolder (QuestionListAdapter.ListViewHolder holder, final int position) {
       final QuestionDetails qd =questionList.get(position);
        holder.ques.setText(qd.getQtag().toString());
        holder.checkbox.setOnCheckedChangeListener(null);
        holder.checkbox.setChecked(questionList.get(position).isSelected());
        holder.checkbox.setTag(questionList.get(position));
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                QuestionDetails contact = (QuestionDetails) cb.getTag();
                contact.setSelected(cb.isChecked());
                questionList.get(position).setSelected(cb.isChecked());
            }
        });


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
            if(Main2Activity.type.equals("multidevices"))
            {
                int itemPosition =QuestionList.recList.getChildLayoutPosition(itemView);
                String item = questionList.get(itemPosition).getQuestion();
           Intent intent = new Intent(parentAct , ViewQuestion.class);
           intent.putExtra("question",item);
            parentAct.startActivity(intent);
            }
        }
    }
    public List<QuestionDetails> getQuestionList() {
        return questionList;
    }

}
