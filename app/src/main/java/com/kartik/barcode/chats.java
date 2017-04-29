package com.kartik.barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class chats extends AppCompatActivity
{
    ArrayList<String> arrayList;
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fauth;
    EditText e1,e2;
    Button b1;
    ArrayList<chatt> friendsList;
    cadapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ca=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clayout);

        lv = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        b1 = (Button) findViewById(R.id.button11);

        database = FirebaseDatabase.getInstance();

        fauth = FirebaseAuth.getInstance();

        reference = database.getReference("messages");
        e1 = (EditText) findViewById(R.id.editText13);
        friendsList = new ArrayList<chatt>();

        reference.addChildEventListener(listner);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
       // Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //friendsList.add(new chatt(fauth.getCurrentUser().getEmail(), e1.getText().toString()));
                reference.push().setValue(new chatt(fauth.getCurrentUser().getEmail(),e1.getText().toString(),formattedDate));
                //ca.notifyDataSetChanged();
            }
        });

        ca = new cadapter(this, friendsList);
        lv.setAdapter(ca);
    }

    ChildEventListener listner=new ChildEventListener()
    {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s)
        {
            chatt c=dataSnapshot.getValue(chatt.class);
            friendsList.add(c);
            ca.notifyDataSetChanged();
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