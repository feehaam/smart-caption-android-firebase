package com.example.smartcaption;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Stats {
    void addLike(Captions caption){
        if(alreadyLiked(caption.id))
            return;
        caption.setFavorite(caption.getFavorite() + 1);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("all_captions");
        reference.child(String.valueOf(caption.getId())).setValue(caption);
    }
    void addSeen(Captions caption){
        caption.setSeen(caption.getSeen() + 1);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("all_captions");
        reference.child(String.valueOf(caption.getId())).setValue(caption);
    }
    void changeActive(Captions caption, String status){
        caption.setActive(status);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("all_captions");
        reference.child(String.valueOf(caption.getId())).setValue(caption);
    }
    private boolean alreadyLiked(int id){

        return false;
    }
}
