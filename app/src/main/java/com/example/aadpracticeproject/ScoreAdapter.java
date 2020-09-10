package com.example.aadpracticeproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    ArrayList<SkillIQ>scoreNum;
    public ScoreAdapter(ArrayList<SkillIQ> scoreSkill){
        this.scoreNum = scoreSkill;

    }
    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup child, int i) {
        Context context = child.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.skill_score_item,child,false);


        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder scoreViewHolder, int i) {

        SkillIQ scoreQ = scoreNum.get(i);
        scoreViewHolder.bind(scoreQ);
    }

    @Override
    public int getItemCount() {
        return scoreNum.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder{

        TextView txtSkillLearner;
        TextView txtSkillScore;
        TextView txtSkillCountry;


        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSkillLearner = (TextView) itemView.findViewById(R.id.txt_name_learner);
            txtSkillScore = (TextView) itemView.findViewById(R.id.txt_skill_score);
            txtSkillCountry = (TextView) itemView.findViewById(R.id.txt_skill_country);
        }
        public void bind  (SkillIQ score){
            txtSkillLearner.setText(score.name);
            txtSkillScore.setText(score.score + " skillIQ score");
            txtSkillCountry.setText(score.country);
        }
    }
}
