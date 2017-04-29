package com.kartik.barcode;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class splash extends AppCompatActivity
{
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    //img=(ImageView)findViewById(R.id.imageView);
//           setContentView(img);

        Thread m=new Thread();
        k n=new k();
        n.execute("xyz","abc");
    }

    class k extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params)
        {
            try
            {
                Thread.sleep(3000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean)
        {
            super.onPostExecute(aBoolean);

            Intent intent = new Intent();
            intent.setClass(splash.this, MainActivity.class);
            overridePendingTransition(R.xml.fade_in, R.xml.fade_out);
            startActivity(intent);
            finish();
        }
    }
}