package com.example.smartcaption;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.Map;

public class CaptionsList extends AppCompatActivity {

    // variables
    int lastID = -1, totalItem = -1; // Initially these two are set to below zero meaning that there is no item yet.
    LinkedList <Captions> captions = new LinkedList <Captions> (); // A list of captions.
    String CATEGORY; // This is the category that is selected by user and passed through intent.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captions_list);

        // Reading the category that is clicked by the user and passed through the intent.
        CATEGORY = getIntent().getStringExtra("Category");
        TextView titleTop = (TextView) findViewById(R.id.titleTop);
        if(CATEGORY.equals("All"))
            titleTop.setText("All Captions");
        else titleTop.setText(CATEGORY);

        // Loading the whole list from the database.
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

                    // Load everything
                    load(lastID);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CaptionsList.this, "Error Loading from database!", Toast.LENGTH_LONG).show();
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
                        if(active.equals("YES")){

                            //System.out.println("CATEGORY = "+CATEGORY+" || Category = "+category);

                            // If user has chosen to see all the captions than the caption will be added into the to-show list.
                            if(CATEGORY.equals("All")){
                                Captions C = new Captions(id, text, owner, category, active, seen, favorite);
                                captions.add(C);
                                loadScreen();
                            }

                            // If the user has chosen a specific category than the caption will be added into to-show list only if it matches the category.
                            else if(CATEGORY.equals(category)){
                                Captions C = new Captions(id, text, owner, category, active, seen, favorite);
                                captions.add(C);
                                loadScreen();
                            }
                        }
//                        else {
//                            // As the previous item is not active (has any problem) and this item is wested, so number of total item to be shown is getting reduced
//                            totalItem--;
//                        }
//                        if(captions.size() == totalItem){
//                            // Load the screen only when full list is loaded from the database. This is like a continue statement for the previous items.
//                            loadScreen();
//                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(CaptionsList.this, "Error Loading from database!", Toast.LENGTH_LONG).show();
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
        MyListAdapter adapter=new MyListAdapter(this, id, titles, seen,fav, category, active, owner, imgid);
        // Adapter set up into the list.
        list.setAdapter(adapter);
        // Rest of the design work continues in MyListAdapter class.

        // Opening the post
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println(captions.get(position).getText());
                System.out.println(captions.get(position).getId());
                Captions clickedCaption;
                clickedCaption = new Captions(captions.get(position).getId(), titles[position], owner[position], category[position], active[position], Integer.parseInt(seen[position]), Integer.parseInt(fav[position]));
                new Stats().addSeen(clickedCaption);
                Intent openPost = new Intent(getApplicationContext(), Post.class);
                openPost.putExtra("Category", captions.get(position).getCategoris()+"");
                openPost.putExtra("Owner", captions.get(position).getOwner()+"");
                openPost.putExtra("Seen", captions.get(position).getSeen()+"");
                openPost.putExtra("Fav", captions.get(position).getFavorite()+"");
                openPost.putExtra("Text", captions.get(position).getText()+"");
                startActivity(openPost);
            }
        });
    }
}