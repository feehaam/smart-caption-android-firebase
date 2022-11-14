package com.example.smartcaption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Category extends AppCompatActivity {

    TextView c1, c2, c3, c4, c5, c6, c7, c8, c9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        c1 = (TextView) findViewById(R.id.funny);
        c2 = (TextView) findViewById(R.id.quotes);
        c3 = (TextView) findViewById(R.id.wish);
        c4 = (TextView) findViewById(R.id.romantic);
        c5 = (TextView) findViewById(R.id.religious);
        c6 = (TextView) findViewById(R.id.festival);
        c7 = (TextView) findViewById(R.id.poemandsong);
        c8 = (TextView) findViewById(R.id.depressed);
        c9 = (TextView) findViewById(R.id.nature);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c1.getText().toString());
                startActivity(openList);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c2.getText().toString());
                startActivity(openList);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c3.getText().toString());
                startActivity(openList);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c4.getText().toString());
                startActivity(openList);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c5.getText().toString());
                startActivity(openList);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c6.getText().toString());
                startActivity(openList);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c7.getText().toString());
                startActivity(openList);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c8.getText().toString());
                startActivity(openList);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openList = new Intent(getApplicationContext(), CaptionsList.class);
                openList.putExtra("Category", c9.getText().toString());
                startActivity(openList);
            }
        });

    }
}