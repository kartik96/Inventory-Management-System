package com.kartik.barcode;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by Kartik on 03-10-2016.
 */

public class chatadap extends BaseAdapter
{

    ArrayList<chatclass> list;
    Context context;
    LayoutInflater layoutInflater;
    FirebaseAuth fauth;


    public chatadap(Context context, ArrayList<chatclass> list)
    {
        super();
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        fauth = FirebaseAuth.getInstance();
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        chatclass i = list.get(position);

        if (fauth.getCurrentUser().getEmail().equals(i.getEmail()))
        {
            convertView = layoutInflater.inflate(R.layout.list_right, null);
            TextView rtxt2 = (TextView) convertView.findViewById(R.id.rightchat);
            TextView rtxt3 = (TextView) convertView.findViewById(R.id.rightchat1);
            rtxt2.setText(i.getEmail());
            rtxt3.setText(i.getMessage());
            Log.e("sender is ", i.getEmail() + " first");

        }

        else
        {
            convertView = layoutInflater.inflate(R.layout.list_left, null);
            TextView ltxt = (TextView) convertView.findViewById(R.id.leftchat);
            TextView ltxt1 = (TextView) convertView.findViewById(R.id.leftchat1);
            ltxt.setText(i.getEmail());
            ltxt1.setText(i.getMessage());
            Log.e("sender is ", i.getEmail() + "   second");
        }
        // Log.e("sender is ", i.getEmail());


        return convertView;
    }

}