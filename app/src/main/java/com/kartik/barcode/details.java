package com.kartik.barcode;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class details extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    Button b1,b2,b3,b4,b5;
    EditText ed;
    TextView tv,tv2;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fauth;
    String q;
    private DrawerLayout drl;
    private ActionBarDrawerToggle abt;
    String f1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Details");
        setContentView(R.layout.activity_details);
        drl=(DrawerLayout) findViewById(R.id.dlayout);
        abt= new ActionBarDrawerToggle(this,drl,R.string.open,R.string.close);
        drl.addDrawerListener(abt);

        abt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1=(Button)findViewById(R.id.button8);
        b3=(Button)findViewById(R.id.button5);
        b4=(Button)findViewById(R.id.button12);
        NavigationView navview=(NavigationView) findViewById(R.id.navv);
        navview.setNavigationItemSelectedListener(this);
        //tv=(TextView)findViewById(R.id.textView13);
        database = FirebaseDatabase.getInstance();
        fauth = FirebaseAuth.getInstance();
        f1=fauth.getCurrentUser().getEmail();
        //tv2=(TextView)findViewById(R.id.textView14);
        //tv2.setText(f1);
        reference= database.getReference();
        ed=(EditText)findViewById(R.id.editText12);
        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String er=fauth.getCurrentUser().getUid();
                reference.child(er).child("status").setValue("inactive");
                fauth.getInstance().signOut();
                Intent r=new Intent(details.this,MainActivity.class);
                startActivity(r);
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent t=new Intent(details.this,chats.class);
                startActivity(t);
            }
        });

        b2=(Button)findViewById(R.id.button9);
        database=FirebaseDatabase.getInstance();
        reference= database.getReference();
        final Activity activity=this;
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentIntegrator in = new IntentIntegrator(activity);
                in.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                in.setPrompt("scan");
                in.setCameraId(0);
                in.setBeepEnabled(false);
                //in.setBarcodeImageEnabled(false);
                in.initiateScan();
            }
        });

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(details.this,"Your Data is:"+ "Notebook,"+"This is a Notebook",Toast.LENGTH_LONG).show();
                //Intent u=new Intent(details.this,records.class);
                //u.putExtra("scr",q);
                //startActivity(u);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(abt.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {
                Log.d("MainActivity","Cancelled scan");
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show();;
            }
            else
            {
                Log.d("MainActivity","Scanned");

        reference.child(result.getContents()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
//                Product p= dataSnapshot.getValue(Product.class);
  //              Log.e("Name",p.Pname);
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
                ed.setText(result.getContents());
                Toast.makeText(this, "Scanned", Toast.LENGTH_SHORT).show();
               // tv.setText(result.getContents());
                q=ed.getText().toString();
            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
        // super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_a:
                Intent kar=new Intent(details.this,chat.class);
                startActivity(kar);
                break;

            case R.id.logout:
                String er=fauth.getCurrentUser().getUid();
                reference.child(er).child("status").setValue("inactive");
                fauth.getInstance().signOut();
                Intent r=new Intent(details.this,MainActivity.class);
                startActivity(r);
                finish();
                break;
        }

        DrawerLayout d1=(DrawerLayout) findViewById(R.id.dlayout);
        if(d1.isDrawerOpen(GravityCompat.START))
        {
            d1.closeDrawer(GravityCompat.START);
        }
        return false;
    }
}