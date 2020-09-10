package com.example.aadpracticeproject;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiConnect {
    private ApiConnect(){};
    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/hours";
//public static final String BASE_API_URL =
//        "https://www.googleapis.com/books/v1/volumes";
    public static final  String QUERY_PARAMETER_KEY = "q";
    public static final  String KEY = "key";
    public static final  String  API_KEY = "AIzaSyCf7EuctJLzcmy7Tp-m9AzNTD4ApgZUu_M";

    public  static URL buildUrl(String value){
       // String fullUrl = BASE_API_URL + "?q=" + name;

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY, value)
                .appendQueryParameter(KEY,API_KEY)
                .build();


        try {
         //   url= new URL(fullUrl);
            url = new URL(uri.toString());

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return url;
    }

    public  static String getJson (URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    try {
        InputStream stream = connection.getInputStream();
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\A");
        boolean hasData = scanner.hasNext();
        if (hasData){
            return scanner.next();
        }
        else {
            return null;
        }

    }
    catch (Exception ex){
        Log.d("Error", ex.toString());
        return null;
    }
    finally {
        connection.disconnect();
    }



    }
    public static ArrayList<Learner> getLearnersFromJson(String json){
        final String  NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
       //final String ID = "id";
        final String ITEMS = "items";
        json = "{ items: " +  json +" }";

      ArrayList<Learner> learners = new ArrayList<Learner>();

      try {
          //  JSONArray arraylearners = new JSONArray(String.valueOf(getLearnersFromJson(json)));
          JSONObject jsonLearners = new JSONObject(json);
           JSONArray arraylearners = jsonLearners.getJSONArray(ITEMS);
          int numberOfLearners = arraylearners.length();

          for (int counter = 0; counter < numberOfLearners; counter++){
              JSONObject learnerJSON = arraylearners.getJSONObject(counter);

           Learner   learner= new Learner  (
                   learnerJSON.getString(NAME),
                      learnerJSON.getString(HOURS),
                      learnerJSON.getString(COUNTRY),
                      learnerJSON.getString(BADGEURL));

              learners.add(learner);

          }
      }
      catch (JSONException ex){
          ex.printStackTrace();
      }

      return learners;

    }

}
