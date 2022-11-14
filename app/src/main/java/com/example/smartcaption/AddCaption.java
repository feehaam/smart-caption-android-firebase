package com.example.smartcaption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class AddCaption extends AppCompatActivity {

    // Last id
    int lastID = -1;

    // Database variables
    FirebaseDatabase rootNode;
    DatabaseReference reference, idReference;

    // XML variables
    TextView designName;
    EditText designText;
    Spinner designCategory;
    Button add;

    // Java variables
    private String text, owner, category, active;
    private int seen, favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caption);

        // Set up last id's
        getLastId();

        // Connecting with database
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("all_captions");
        idReference = rootNode.getReference("id");

        // Connecting XML and Java
        designName = (TextView) findViewById(R.id.name);
        designCategory = (Spinner) findViewById(R.id.category);
        designText = (EditText) findViewById(R.id.text);
        add = (Button) findViewById(R.id.add);

        // Setting up drop down (spinner) category contents
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designCategory.setAdapter(adapter);

        // Setting up add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = designText.getText().toString();
                owner = designName.getText().toString();
                category = String.valueOf(designCategory.getSelectedItem());
                active = "NO";
                seen = 0;
                favorite = 0;

                if(text == null || text.length() < 10){
                    Toast.makeText(AddCaption.this, "Add a caption (at least 10 latters)!", Toast.LENGTH_LONG).show();
                }
                else if(owner == null || owner.length()<3){
                    Toast.makeText(AddCaption.this, "You must enter your name!", Toast.LENGTH_LONG).show();
                }
                else if(category == null || category.equals("Select a category")){
                    Toast.makeText(AddCaption.this, "Please select a category!", Toast.LENGTH_LONG).show();
                }
                else{
                    // Making a caption object
                    Captions caption = new Captions(lastID, text, owner, category, active, seen, favorite);

                    // Inserting the caption object into the database
                    reference.child(String.valueOf(lastID++)).setValue(caption);

                    // Resetting all the fields
                    text = null;
                    owner = null;
                    category = null;
                    designName.setText("");
                    designCategory.setSelection(0);
                    designText.setText("");
                    increaseLastId();

                    // Successful message
                    Toast.makeText(AddCaption.this, "New caption is added!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Reading last id
    private void getLastId(){

        // Database variables
        DatabaseReference rootDatabaseref;
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("id");

        // Using the onDataChange method among three different data reading methods from firebase.
        rootDatabaseref.child("last").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                    lastID = Integer.parseInt(String.valueOf(map.get("all")));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Whenever a new caption is inserted the last ID should be increased
    private void increaseLastId(){
        // Setting the new incremented (in line #91) value in database>id>last>all as new value for next new item.
        idReference.child("last").child("all").setValue(lastID);
    }
}