package com.example.w.a.y;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class Posting extends Activity {
	EditText ed1,ed2;
	ArrayList<Team> teams = new ArrayList<Team>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed1 = (EditText)findViewById(R.id.editText1);
		ed2 = (EditText)findViewById(R.id.editText2);
	}

	
	public void function1(View v)
	{
		try{
		String fullURL = "https://docs.google.com/forms/d/1rTepRwacdiIBE4c99M4gUOrGpdbkuQLwoKsJk-czrDM/formResponse";
		HttpRequest mReq = new HttpRequest();
		String col1 = ed1.getText().toString();
		String col2 = ed2.getText().toString();
		String data = "entry_1944369324=" + URLEncoder.encode(col1) + "&" + "entry_1061056021=" + URLEncoder.encode(col2);
		String response = mReq.sendPost(fullURL, data);
		Toast.makeText(this, "Posted", 2000).show();
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Error", 2000).show();
		}
	}
	public void function2(View v)
	{
		ed1.setText(" ");
		ed2.setText(" ");
	}
	public void function3(View v)
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
 
            new DownloadWebpageTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                processJson(object);
                	}
            }).execute("https://spreadsheets.google.com/tq?key=14GNvlWn_NmlRTPWqjLox7n7zZaYu9FrV9hi7SAM04oo");


        
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	


    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");
           
            for (int  r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String position = columns.getJSONObject(0).getString("v");
                String name = columns.getJSONObject(1).getString("v");
                Team team = new Team(position, name);
                teams.add(team);
              }
            
            Team a = teams.get(2);
            ed1.setText(a.position);
            ed2.setText(a.name);
            
 


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	
}

