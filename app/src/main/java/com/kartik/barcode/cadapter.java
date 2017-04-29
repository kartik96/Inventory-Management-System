package com.kartik.barcode;

/**
 * Created by Kartik on 12-07-2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class cadapter  extends BaseAdapter
{

    LayoutInflater LI;
    ArrayList FriendsList;

    cadapter(Context con, ArrayList<chatt> al)
    {
        LI = (LayoutInflater) con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
        //FriendsList=new ArrayList();
        FriendsList = al;
    }

    @Override
    public int getCount() {
        return FriendsList.size();
    }

    @Override
    public Object getItem(int i) {
        return FriendsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v;
        if (view == null)
        {
            v = LI.inflate(R.layout.customlist, null);
        }

        else
        {
            v = view;
        }
        TextView tvName = (TextView) v.findViewById(R.id.textView4);
        TextView tvMessage = (TextView) v.findViewById(R.id.textView5);
        TextView tvdate = (TextView) v.findViewById(R.id.textView11);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        //Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        chatt f = (chatt) getItem(i);
        tvName.setText(f.getName());
        tvMessage.setText(f.getMessage());
        tvdate.setText("" + formattedDate);
       // iv.setBackgroundResource(f.getImage());
        return v;
    }
}