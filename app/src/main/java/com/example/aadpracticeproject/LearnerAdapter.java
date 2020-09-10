package com.example.aadpracticeproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.HoursViewHolder> {
        ArrayList<Learner> Hours;

        public LearnerAdapter(ArrayList<Learner> hoursDone){
            this.Hours = hoursDone;
        }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_learner, viewGroup,false);


        return new HoursViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder hoursViewHolder, int i) {

            Learner hour = Hours.get(i);
            hoursViewHolder.bind(hour);

    }

    @Override
    public int getItemCount() {
        return Hours.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameLearner;
        TextView txtHourValue;
        TextView txtHourCountry;
        public HoursViewHolder(@NonNull View itemView) {

            super(itemView);
            txtNameLearner = (TextView) itemView.findViewById(R.id.learner_name);
            txtHourValue = (TextView) itemView.findViewById(R.id.learner_score);
            txtHourCountry = (TextView) itemView.findViewById(R.id.learner_country);

        }
        public void bind (Learner hour){
            txtNameLearner.setText(hour.name);
            txtHourValue.setText(hour.hours +" learnimg hours ");
            txtHourCountry.setText(hour.country);
        }
    }


}
