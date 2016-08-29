package com.example.maseera.hierarchyviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class First extends AppCompatActivity {

    String htmlString="";
    ArrayList<Hierarchy> hierarchyArrayList = new ArrayList<Hierarchy>();
    String user="";
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

        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            user = extras.getString("USERNAME");
        }

        TextView textView = (TextView)findViewById(R.id.textView_user);
        textView.setText("Hello " + user + "!");

        Button btn = (Button)findViewById(R.id.button_ViewHierarchy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(First.this,Main2Activity.class);
                intent1.putExtra("param", htmlString);
                First.this.startActivity(intent1);

            }
        });


             Button table = (Button)findViewById(R.id.button_table);
             table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(First.this,Main3Activity.class);
                intent2.putExtra("object",  hierarchyArrayList);
                First.this.startActivity(intent2);

            }
        });

        new GetAllCustomerTask().execute(new ApiConnector());
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

               startActivity(new Intent(First.this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setTextToTextView(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                Hierarchy hierarchy = new Hierarchy(json.getString("Position"), json.getString("Reports To Position"), json.getString("Name of Incumbent"));
                hierarchyArrayList.add(hierarchy);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        for (int i = 0; i < jsonArray.length(); i++) {

            if(i!=0)
            {
                htmlString = htmlString + ",";
            }

            htmlString=htmlString + "[{v:\'"+hierarchyArrayList.get(i).position + "\', f:\'"+hierarchyArrayList.get(i).nameOfIncumbent+"<div style=\"color:red; font-style:italic\">" + hierarchyArrayList.get(i).position+"</div>'},\'" +hierarchyArrayList.get(i).reportsToPosition +"\',\'" + hierarchyArrayList.get(i).position+"\']";

        }
        Log.e("check", "String" + htmlString);

    }



    private class GetAllCustomerTask extends AsyncTask<ApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread
            try {
                return params[0].GetAllCustomers();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            setTextToTextView(jsonArray);

        }
    }

}
