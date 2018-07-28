package com.eissa.leddancer;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        txtView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                startActivityForResult(intent, 123);
            }
        });
    }

    private void sendRequest(String command) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://serverIp:8080/LEDDancer/command?name=" + command;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        txtView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {

            ArrayList<String> matches_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches_text.contains("open red")) {
                sendRequest("openRed");
            } else if (matches_text.contains("close red")) {
                sendRequest("closeRed");
            } else if (matches_text.contains("open green")) {
                sendRequest("openGreen");
            } else if (matches_text.contains("close green")) {
                sendRequest("closeGreen");
            } else if (matches_text.contains("open blue")) {
                sendRequest("openBlue");
            } else if (matches_text.contains("close blue")) {
                sendRequest("closeBlue");
            } else if (matches_text.contains("open yellow")) {
                sendRequest("openYellow");
            } else if (matches_text.contains("close yellow")) {
                sendRequest("closeYellow");
            } else if (matches_text.contains("dance")) {
                sendRequest("dance");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
