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

public class LearnerActivity extends AppCompatActivity {

    private RecyclerView hourRecyclerView;
    private ProgressBar loadrogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnerctivity);

        hourRecyclerView = (RecyclerView) findViewById(R.id.txt_hours);
        loadrogressBar = (ProgressBar) findViewById(R.id.prog_load);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        hourRecyclerView.setLayoutManager(layoutManager);


        try {
            URL learnerUrl = ApiConnect.buildUrl("name");
            new LearnerActivity.LearnerHour().execute(learnerUrl);

        }
        catch (Exception ex){
        Log.d("Error", ex.getMessage() );
        }
    }
    public class LearnerHour extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl =urls[0];
            String result =null;

            try {
               result =ApiConnect.getJson(searchUrl);
            }
            catch (IOException ex){
                Log.d("Error", ex.getMessage());

            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
           // super.onPostExecute(s);

            TextView textError = (TextView) findViewById(R.id.txt_Error_loading);
            loadrogressBar.setVisibility(View.INVISIBLE);

            if (result == null) {
                textError.setVisibility(View.VISIBLE);
                hourRecyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                textError.setVisibility(View.INVISIBLE);
                hourRecyclerView.setVisibility(View.VISIBLE);
            }
            ArrayList<Learner> learners = ApiConnect.getLearnersFromJson(result);


           LearnerAdapter adapter = new LearnerAdapter(learners);
            hourRecyclerView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadrogressBar.setVisibility(View.VISIBLE);
        }
    }
}