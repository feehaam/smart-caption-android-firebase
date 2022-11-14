package com.example.smartcaption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Post extends AppCompatActivity {

    TextView details, seen, fav, text;
    Button copy;

    String CAT, OWNER, TEXT, SEEN, FAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        details = (TextView) findViewById(R.id.details);
        seen = (TextView) findViewById(R.id.seen);
        fav = (TextView) findViewById(R.id.fav);
        text = (TextView) findViewById(R.id.text);
        copy = (Button) findViewById(R.id.copy);

        Intent I = getIntent();
        CAT = I.getStringExtra("Category");
        SEEN = I.getStringExtra("Seen");
        FAV = I.getStringExtra("Fav");
        OWNER = I.getStringExtra("Owner");
        TEXT = I.getStringExtra("Text");

        details.setText("Category: "+CAT+"\nPosted by: "+OWNER);
        seen.setText(SEEN);
        fav.setText(FAV);
        text.setText(TEXT);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cbm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd = ClipData.newPlainText("Post", text.getText().toString());
                cbm.setPrimaryClip(cd);
                Toast.makeText(Post.this, "Caption copied!", Toast.LENGTH_LONG).show();
            }
        });
    }
}