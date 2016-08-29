package com.example.maseera.hierarchyviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

   ArrayList<Hierarchy> hierarchyArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive","Logout in progress");
                finish();
            }
        }, intentFilter);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Table");

            hierarchyArrayList = (ArrayList<Hierarchy>)getIntent().getSerializableExtra("object");

        init();
    }

    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        tbrow0.setBackgroundResource(R.color.colorPrimary);

        TextView tv0 = new TextView(this);
        tv0.setBackgroundResource (android.R.drawable.edit_text);
        tv0.setText("Position");
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setBackgroundResource (android.R.drawable.edit_text);
        tv1.setText(" Reports To Position ");
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setBackgroundResource (android.R.drawable.edit_text);
        tv2.setText(" Name ");
        tbrow0.addView(tv2);
        stk.addView(tbrow0);

        for (int i = 0; i < hierarchyArrayList.size(); i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setBackgroundResource(R.color.colorAccent);

            TextView t1v = new TextView(this);
            t1v.setBackgroundResource(android.R.drawable.edit_text);
            t1v.setText (hierarchyArrayList.get(i).position);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setBackgroundResource(android.R.drawable.edit_text);
            t2v.setText(hierarchyArrayList.get(i).reportsToPosition);
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setBackgroundResource(android.R.drawable.edit_text);
            t3v.setText(hierarchyArrayList.get(i).nameOfIncumbent);
            tbrow.addView(t3v);
            stk.addView(tbrow);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                sendBroadcast(broadcastIntent);

                startActivity(new Intent(Main3Activity.this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

