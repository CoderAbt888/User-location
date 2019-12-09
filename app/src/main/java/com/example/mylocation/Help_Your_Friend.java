package com.example.mylocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Help_Your_Friend extends AppCompatActivity implements ValueEventListener {

   // DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase firebaseDatabaseref = FirebaseDatabase.getInstance();
    DatabaseReference dbref = firebaseDatabaseref.getReference();
     EditText name;
     EditText neede;
    /////////////////////
    TextView textView;

    DatabaseReference text    = dbref.child("cool");
     String nameu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__your__friend);



         neede = (EditText)findViewById(R.id.Need);
        name =(EditText)findViewById(R.id.Name);
        nameu = name.getText().toString();



//
//        Map<String,String> values = new HashMap<>();
//        values.put("Name","Abhay");
//        dbref.push().setValue(values, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                if (databaseError == null) {
//                    Log.i("info","Done");
//                }else{
//
//                    Log.i("info","fucked");
//                }
//
//            }
//        });







    }
    public void submit(View vie){





    }


    public void need(View vie){


        String reque = neede.getText().toString();
        text.setValue(reque);
        name.setText("");
        neede.setText("");

    }


    @Override
    public void onDataChange( DataSnapshot dataSnapshot) {

        String good = nameu;
        if(dataSnapshot.getValue(String.class)!=null){



            String key = dataSnapshot.getKey();
           if(key.equals("cool")) {
               String helo = dataSnapshot.getValue(String.class);
               textView = (TextView) findViewById(R.id.newText);
               ///String helo = "hokeghghghgffg" +
                   //
               //    "d";



               textView.setText( good+"you  needs " +
                       ""+ helo);


           }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        text.addValueEventListener(this);


    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
