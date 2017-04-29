package com.kartik.barcode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Kartik on 03-10-2016.
 */

public class chat extends Activity
{
    FirebaseDatabase database;
    DatabaseReference dbreference;
    FirebaseAuth fauth;
    ListView lv;
    ArrayList<chatclass> list;
    chatadap adap;
    EditText et;
    TextView chatHead;
    Button b;
    String[] chatheadingarr1, chatheadingarr2, total;
    String chatheading, Bname, Bauthor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clayout);
        database = FirebaseDatabase.getInstance();
        dbreference = database.getReference();
        list = new ArrayList<chatclass>();
        fauth = FirebaseAuth.getInstance();
        adap = new chatadap(this, list);
        et = (EditText) findViewById(R.id.editText13);
        //chatHead = (TextView) findViewById(R.id.chatHeading);
        b = (Button) findViewById(R.id.button11);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adap);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!et.getText().toString().equals(""))
                {
                    String s = fauth.getCurrentUser().getEmail().toString();
                    chatclass c = new chatclass(s, et.getText().toString());
                    //dbreference.child("chat").child(chatheading).push().setValue(c);
                   // dbreference.child("users").child(chatheadingarr1[0]).child("chat").child(chatheading).push().setValue(c);
                    //dbreference.child("users").child(chatheadingarr2[0]).child("chat").child(chatheading).push().setValue(c);
                    dbreference.push().setValue(new chatclass(fauth.getCurrentUser().getEmail(),et.getText().toString()));
                    //dbreference.child("users").child(chatheadingarr1[0]).child("chatname").child(chatheading).setValue(chatheading);
                    //dbreference.child("users").child(chatheadingarr2[0]).child("chatname").child(chatheading).setValue(chatheading);

                    et.setText("");
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    ChildEventListener listner = new ChildEventListener()
    {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s)
        {
            chatclass msg = dataSnapshot.getValue(chatclass.class);
            list.add(msg);
            adap.notifyDataSetChanged();
            //Log.e("added",msg.getMessage());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s)
        {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot)
        {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s)
        {

        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };
}