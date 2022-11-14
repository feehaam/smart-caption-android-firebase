package com.example.smartcaption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addCaption = (Button) findViewById(R.id.addCaption);
        Button captionsList = (Button) findViewById(R.id.captionsList);
        Button moderator = (Button) findViewById(R.id.additional1) ;
        Button cat = (Button) findViewById(R.id.additional2);
        Button more = (Button) findViewById(R.id.more);
        Button exit = (Button) findViewById(R.id.exit);

        addCaption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedActivity = new Intent(getApplicationContext(), AddCaption.class);
                startActivity(selectedActivity);
            }
        });
        captionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedActivity = new Intent(getApplicationContext(), CaptionsList.class);
                selectedActivity.putExtra("Category", "All");
                startActivity(selectedActivity);
            }
        });
        moderator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedActivity = new Intent(getApplicationContext(), MLog.class);
                startActivity(selectedActivity);
            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedActivity = new Intent(getApplicationContext(), Category.class);
                startActivity(selectedActivity);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming soon...", Toast.LENGTH_LONG).show();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}