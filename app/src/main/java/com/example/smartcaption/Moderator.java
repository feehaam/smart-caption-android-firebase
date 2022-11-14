package com.example.smartcaption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.Map;

public class Moderator extends AppCompatActivity {

    // variables
    int lastID = -1, totalItem = -1; // Initially these two are set to below zero meaning that there is no item yet.
    LinkedList<Captions> captions = new LinkedList <Captions> (); // A list of captions.
    String nameOfModerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator);

        // Getting the name of moderator.
        nameOfModerator = new Intent().getStringExtra("name");

        // Loading the whole list from the database.
        System.out.println("Starting loading screen. ");
        loadDatabase();
    }

    void loadDatabase(){

        // Firebase database connection (id path).
        DatabaseReference rootDatabaseref;
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("id");

        // Reading  the last ID value
        rootDatabaseref.child("last").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                    lastID = Integer.parseInt(String.valueOf(map.get("all")));
                    totalItem = lastID;
                    System.out.println("Last id is: "+lastID);

                    // Load everything
                    load(lastID);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Moderator.this, "Error Loading from database!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private int currI = -1;
    private void load(int last){

        // Connecting with the firebase database (all captions list path).
        DatabaseReference rootDatabaseref;
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("all_captions");

        // Reading all items (until last id).
        for(int i=0; i<last; i++){
            rootDatabaseref.child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        // Reading the whole object.
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                        // Extracting values from the object.
                        String text = String.valueOf(map.get("text"));
                        String owner = String.valueOf(map.get("owner"));
                        String category = String.valueOf(map.get("categoris"));
                        String active = String.valueOf(map.get("active"));
                        int seen = Integer.parseInt(String.valueOf(map.get("seen")));
                        int favorite = Integer.parseInt(String.valueOf(map.get("favorite")));
                        int id = Integer.parseInt(String.valueOf(map.get("id")));

                        // Active "YES" means - the post has no problems, unique, bad-word-free and can be shown in the app.
                        if(active.equals("NO")){
                            Captions C = new Captions(id, text, owner, category, active, seen, favorite);
                            captions.add(C);
                            loadScreen();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Moderator.this, "Error Loading from database!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // Till now, the captions list is just loaded from the database, now this method will show all those captions in the captions list activity.
    void loadScreen(){

        // Making spaces/arrays to store the information of the captions.
        int size = captions.size();
        int id[] = new int[size];
        String titles[] = new String[size];
        String seen[] = new String[size];
        String fav[] = new String[size];
        String category[] = new String[size];
        String active[] = new String[size];
        String owner[] = new String[size];
        Integer[] imgid = new Integer[size];

        // For all of the captions from the list is loaded into the arrays.
        for(int i=0; i<captions.size(); i++){
            id[i] = captions.get(i).getId();
            titles[i] = captions.get(i).getText();
            seen[i] = String.valueOf(captions.get(i).getSeen());
            fav[i] = String.valueOf(captions.get(i).getFavorite());
            category[i] = String.valueOf(captions.get(i).getCategoris());
            active[i] = String.valueOf(captions.get(i).getActive());
            owner[i] = String.valueOf(captions.get(i).getOwner());
        }

        // Setting up specific icon based on category.
        for(int i=0; i<captions.size(); i++){
            if(category[i].equals("Quotes"))
                imgid[i] = R.drawable.quotes;
            else if(category[i].equals("Festival"))
                imgid[i] = R.drawable.festival;
            else if(category[i].equals("Depressed"))
                imgid[i] = R.drawable.depressed;
            else if(category[i].equals("Funny"))
                imgid[i] = R.drawable.funny;
            else if(category[i].equals("Wish"))
                imgid[i] = R.drawable.wish;
            else if(category[i].equals("Religious"))
                imgid[i] = R.drawable.religious;
            else if(category[i].equals("Romantic"))
                imgid[i] = R.drawable.romantic;
            else if(category[i].equals("Nature"))
                imgid[i] = R.drawable.nature;
            else if(category[i].equals("Poem and Song"))
                imgid[i] = R.drawable.peomandsong;
        }

        // The list from XML is referenced.
        ListView list = (ListView) findViewById(R.id.list);
        // Adapter set up with arrays
        MyListAdapter2 adapter=new MyListAdapter2(this, id, titles, seen,fav, category, active, owner, imgid, nameOfModerator);
        // Adapter set up into the list.
        list.setAdapter(adapter);
        // Rest of the design work continues in MyListAdapter class.

        if(id.length == 0) {
            TextView title = (TextView) findViewById(R.id.titleTop);
            title.setText("No new items on the list.");
        }
    }
}