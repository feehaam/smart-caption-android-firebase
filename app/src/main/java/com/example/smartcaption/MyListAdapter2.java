package com.example.smartcaption;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MyListAdapter2 extends ArrayAdapter<String> {

    // Arrays and variables for context, caption object, icon and serial no.
    private Activity context;
    private String[] titles, seen, fav, category, active, owner;
    private int SI[];
    private String nameOfModerator;

    // Constructor: Setting up all arrays with the values sent from Captions list class.
    public MyListAdapter2(Activity context, int[] id, String[] titles, String[] seen,String[] fav,String[] category, String[] active, String[] owner, Integer[] img, String moderator) {
        super(context, R.layout.mylist, titles);

        nameOfModerator = moderator;
        this.context=context;
        this.SI = id;
        this.titles=titles;
        this.seen=seen;
        this.fav = fav;
        this.category = category;
        this.active = active;
        this.owner = owner;

    }

    // This class extends ArrayAdapter and overrides this particular method to show items in our own way.
    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist2, null,true);

        // Referencing the items in each row.
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView text = (TextView) rowView.findViewById(R.id.text);

        // Setting up the information
        title.setText("ID: "+SI[position]+" (Cat: "+category[position]+")");
        text.setText(titles[position]);

        // On click listener for delete button: what will happen if delete button is pressed.
        Button delete = (Button) rowView.findViewById(R.id.delete);
        Button approve = (Button) rowView.findViewById(R.id.approve);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Captions clickedCaption = new Captions(SI[position], titles[position], owner[position], category[position], active[position], Integer.parseInt(seen[position]), Integer.parseInt(fav[position]));
                new Stats().addLike(clickedCaption);
                new Stats().changeActive(clickedCaption, "DELETED by "+nameOfModerator);
                delete.setText("Deleted");
                delete.setEnabled(false);
                approve.setEnabled(false);
                Toast.makeText(getContext(), "Item is deleted.", Toast.LENGTH_LONG).show();
            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Captions clickedCaption = new Captions(SI[position], titles[position], owner[position], category[position], active[position], Integer.parseInt(seen[position]), Integer.parseInt(fav[position]));
                new Stats().addLike(clickedCaption);
                new Stats().changeActive(clickedCaption, "YES");
                approve.setText("Approved");
                approve.setEnabled(false);
                delete.setEnabled(false);
                Toast.makeText(getContext(), "Item is approved to main list.", Toast.LENGTH_LONG).show();
            }
        });

        return rowView;

    };
}