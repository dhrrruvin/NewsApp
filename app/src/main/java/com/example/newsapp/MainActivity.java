package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NewsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView newsRecyclerView = findViewById(R.id.newsRecyclerView);

        fetchData();

        mAdapter = new NewsListAdapter(this);

        newsRecyclerView.setAdapter(mAdapter);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    void fetchData() {

        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=PaisaDeKeLoYaar";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    Log.e("TAG", "fetchData: "+response );
                    JSONArray newsJsonArray = new JSONArray();
                    try {
                        newsJsonArray = response.getJSONArray("articles");
                    } catch (JSONException e) {
                        Log.e("MYAPP","Error in articles", e);
                    }
                    ArrayList<News> newsArrayList = new ArrayList<>();
                    for(int i = 0; i< Objects.requireNonNull(newsJsonArray).length(); i++) {
                        JSONObject newsJsonObject = null;
                        try {
                            newsJsonObject = newsJsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            Log.e("MYAPP", "Exception in for loop");
                        }
                        try {
                            assert newsJsonObject != null;
                            News news = new News(
                                    newsJsonObject.getString("title"),
                                    newsJsonObject.getString("author"),
                                    newsJsonObject.getString("url"),
                                    newsJsonObject.getString("urlToImage")
                                    );
                            newsArrayList.add(news);
                        } catch (JSONException e) {
                            Log.e("MYAPP", "Exception in getting data from api");
                        }
                        mAdapter.updateNews(newsArrayList);
                    }
                },
                error -> {

                }
        ){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

}
