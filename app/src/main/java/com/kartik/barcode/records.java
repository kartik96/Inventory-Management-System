package com.kartik.barcode;
/**
 * Created by Kartik on 6/27/2016.
 */

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.util.ArrayList;

public class records extends AppCompatActivity
{
    FirebaseDatabase database;
    DatabaseReference dbreference;
    ListView lv;
    TextView tv;
    ArrayAdapter Adapter;
    ArrayList<String> list;
    String w1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        String w = getIntent().getStringExtra("scr");
        list = new ArrayList<String>();
        tv = (TextView) findViewById(R.id.textView12);
        Adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(Adapter);
        Log.e("Barcode is"," "+w);
        dbreference=database.getReference(w);
        dbreference.addChildEventListener(listner);
        tv.setText("" + w);
        list.add("fuck");
        Adapter.notifyDataSetChanged();
    }
    ChildEventListener listner =new ChildEventListener()
    {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s)
        {
            Product b1 = dataSnapshot.getValue(Product.class);
            list.add(b1.Discription);
            Adapter.notifyDataSetChanged();
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
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError)
        {

        }
    };
}