package com.kartik.barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class display extends AppCompatActivity
{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tv = (TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("scr");
        tv.setText(s);
    }
}