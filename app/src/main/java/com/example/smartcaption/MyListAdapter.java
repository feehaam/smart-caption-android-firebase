package com.example.smartcaption;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MyListAdapter extends ArrayAdapter<String> {

    // Arrays and variables for context, caption object, icon and serial no.
    private Activity context;
    private String[] titles, seen, fav, category, active, owner;
    Integer imgid[];
    int SI[];

    // Constructor: Setting up all arrays with the values sent from Captions list class.
    public MyListAdapter(Activity context, int[] id, String[] titles, String[] seen,String[] fav,String[] category, String[] active, String[] owner, Integer[] img) {
        super(context, R.layout.mylist, titles);

        this.context=context;
        this.SI = id;
        this.titles=titles;
        this.seen=seen;
        this.fav = fav;
        this.category = category;
        this.active = active;
        this.owner = owner;
        this.imgid=img;

    }

    // This class extends ArrayAdapter and overrides this particular method to show items in our own way.
    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        // Referencing the items in each row.
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView seenText = (TextView) rowView.findViewById(R.id.seen);
        TextView favText = (TextView) rowView.findViewById(R.id.fav);
        ImageView like = (ImageView) rowView.findViewById(R.id.like);

        // Setting up the information
        icon.setImageResource(imgid[position]);
        titleText.setText(fixTextSize(titles[position], 100)); // Fix text size: limits the length of caption in a suitable size.
        seenText.setText(seen[position]);
        favText.setText(fav[position]);

        // On click listener for like button: what will happen if someone hits like.
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Captions clickedCaption = new Captions(SI[position], titles[position], owner[position], category[position], active[position], Integer.parseInt(seen[position]), Integer.parseInt(fav[position]));
                //new Stats().addLike(clickedCaption);
                //new Stats().changeActive(clickedCaption, "NO");
                Toast.makeText(getContext(), "This feature is coming soon!", Toast.LENGTH_LONG).show();
            }
        });

//        // When clicked on a caption, it's color will get darker.
//        // Increase the view of caption.
//        titleText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                titleText.setTextColor(Color.parseColor("#000000"));
//                Captions clickedCaption;
//                clickedCaption = new Captions(SI[position], titles[position], owner[position], category[position], active[position], Integer.parseInt(seen[position]), Integer.parseInt(fav[position]));
//                new Stats().addSeen(clickedCaption);
//            }
//        });

        return rowView;

    };

    String fixTextSize(String text, int limit){
        if(text.length() > limit)
        return text.substring(0, limit) + "...";
        else return text;
    }
}