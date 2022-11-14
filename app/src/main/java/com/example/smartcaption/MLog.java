package com.example.smartcaption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MLog extends AppCompatActivity {

    TextView username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlog);

        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                if(name == null || name.length()<1 || pass == null || pass.length()<1)
                    Toast.makeText(MLog.this, "Type username and password correctly!", Toast.LENGTH_LONG).show();

                else if(loginPossible(pass)){
                    Intent openMod = new Intent(getApplicationContext(), Moderator.class);
                    openMod.putExtra("name", name);
                    Toast.makeText(MLog.this, "Login successful.", Toast.LENGTH_LONG).show();
                    startActivity(openMod);
                }

                else{
                    Toast.makeText(MLog.this, "Incorrect login info!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // To ensure security, the password is not even visible from the code.
    private boolean loginPossible(String p){
        if(p.hashCode() == 1794633494)
            return true;
        else return false;
    }
}