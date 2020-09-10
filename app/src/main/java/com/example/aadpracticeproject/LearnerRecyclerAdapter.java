package com.example.aadpracticeproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LearnerRecyclerAdapter extends RecyclerView.Adapter<LearnerRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    
    private final LayoutInflater mLayoutInflater;

    public LearnerRecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup grade, int i) {
        View learnerView = mLayoutInflater.inflate(R.layout.item_list_learner,grade,false);

        return new ViewHolder(learnerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTextLearner;
        public final TextView mTextScore;
        public final TextView mTextCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextLearner = (TextView) itemView.findViewById(R.id.learner_name);
            mTextScore = (TextView) itemView.findViewById(R.id.learner_score);
            mTextCountry = (TextView) itemView.findViewById(R.id.learner_country);

        }
    }

}
