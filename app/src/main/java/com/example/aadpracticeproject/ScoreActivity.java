package com.example.aadpracticeproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mProgressBar =(ProgressBar)  findViewById(R.id.progress_loading);
        mRecyclerView = (RecyclerView) findViewById(R.id.txt_score);
        LinearLayoutManager scoreLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(scoreLayoutManager);


        try {
            URL scoreUrl = ApiConnecctSkill.buildUrl("name");
            new ScoreActivity.GetScores().execute(scoreUrl);
        }
        catch (Exception ex){
            Log.d("Error", ex.getMessage() );
        }
    }

    public class GetScores  extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL stringUrl = urls[0];
            String result = null;

            try {
              result = ApiConnecctSkill.getJson(stringUrl);

            }
            catch (IOException ex){
                Log.d("Error", ex.getMessage());
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textError = (TextView) findViewById(R.id.txt_score_loading);
            mProgressBar.setVisibility(View.INVISIBLE);

            if (result == null) {
                textError.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                textError.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
            ArrayList<SkillIQ> scores = ApiConnecctSkill.getScoresFromJson(result);
            String resultString = "";



            ScoreAdapter adapter = new ScoreAdapter(scores);
            mRecyclerView.setAdapter(adapter);
        }
    }
}