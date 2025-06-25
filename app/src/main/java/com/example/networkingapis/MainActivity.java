package com.example.networkingapis; // Ensure this matches your package name

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast; // For showing short messages to the user

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Tag for Logcat messages
    private RequestQueue requestQueue; // Volley RequestQueue for network requests
    private RecyclerView recyclerView; // RecyclerView to display posts
    // Initialize the adapter with an empty list initially
    private MyAdapter adapter = new MyAdapter(Collecgittions.<com.example.networkingapis.PostPost>unmodifiableList(new ArrayList<>())); // Adapter for the RecyclerView
    private Button fetchDataButton; // Button to trigger data fetch

    // The API endpoint URL for JSONPlaceholder posts
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout from activity_main.xml

        // Initialize UI components
        fetchDataButton = findViewById(R.id.fetchDataButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize Volley RequestQueue.
        // Using getApplicationContext() ensures the RequestQueue lives for the lifetime of the app.
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        // Set an OnClickListener for the fetch data button
        fetchDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDataFromApi(); // Call the method to fetch data when the button is clicked
            }
        });
    }

    /**
     * Initiates an HTTP GET request to the specified API_URL using Volley.
     * Parses the JSON response and updates the RecyclerView.
     */
    private void fetchDataFromApi() {
        // Show a toast message to indicate data fetching is in progress
        Toast.makeText(this, "Fetching data...", Toast.LENGTH_SHORT).show();

        // Create a StringRequest for a GET request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // This method is called when a successful response is received
                        Log.d(TAG, "API Response: " + response); // Log the raw JSON response

                        try {
                            // Parse the JSON response. JSONPlaceholder returns a JSON Array.
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<PostPost> posts = new ArrayList<>();

                            // Loop through each JSON object in the array
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject postObject = jsonArray.getJSONObject(i);

                                // Extract data from the JSON object
                                int userId = postObject.getInt("userId");
                                int id = postObject.getInt("id");
                                String title = postObject.getString("title");
                                String body = postObject.getString("body");

                                // Create a new Post object and add it to the list
                                PostPost post;
                                post = new com.example.networkingapis.Post().Post(userId, id, title, body);
                                posts.add(post);
                            }

                            // Update the adapter with the new list of posts
                            adapter.setPosts(posts);
                            Toast.makeText(MainActivity.this, "Data fetched successfully!", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            // Handle JSON parsing errors
                            Log.e(TAG, "JSON parsing error: " + e.getMessage());
                            Toast.makeText(MainActivity.this, "Error parsing data.", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // This method is called when an error occurs during the request
                        Log.e(TAG, "Volley error: " + error.getMessage());
                        Toast.makeText(MainActivity.this, "Error fetching data: " + error.getMessage(), LENGTH_LONG).show();
                        error.printStackTrace(); // Print stack trace for debugging
                    }
                });

        // Add the request to the RequestQueue to execute it
        requestQueue.add(stringRequest);
    }
}