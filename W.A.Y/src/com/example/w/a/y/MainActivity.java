package com.example.w.a.y;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AnimationListener {
	Animation animfade, animmove, animout;
	ImageView iv2,iv3,ivb1,ivb2,ivb3,ivb4,ivb5,ivb6,ivb7,ivb8,ivbb,ivbl,iv6,ivtwo,ivthree,ivfour,ivfive,ivsix,ivseven,iveight,ivb,ivl;
	EditText ed1,ed2,eds;
	TextView tv1,tv2;
	String x,y;
	ArrayList<Team> teams = new ArrayList<Team>();
	Button bt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		animfade = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
		animfade.setAnimationListener(this);
		animout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
		animout.setAnimationListener(this);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		eds = (EditText) findViewById(R.id.editTextsearch);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		bt1 = (Button) findViewById(R.id.button1);
		//iv2.setVisibility(View.INVISIBLE);
		ed1.setVisibility(View.VISIBLE);
		ed2.setVisibility(View.VISIBLE);
		tv1.setVisibility(View.VISIBLE);
		tv2.setVisibility(View.VISIBLE);
		bt1.setVisibility(View.VISIBLE);
		ed1.startAnimation(animfade);
        ed2.startAnimation(animfade);
        tv1.startAnimation(animfade);
        tv2.startAnimation(animfade);
        bt1.startAnimation(animfade);
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
                String wins = columns.getJSONObject(2).getString("v");
                String draws = columns.getJSONObject(3).getString("v");
                Team team = new Team(position, name,wins,draws);
                teams.add(team);
              }
            
            Toast.makeText(getApplicationContext(), "Reached", 2000).show();
            
 


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	public void firsttrans(View v)
	{
		x = ed1.getText().toString();
		y = ed2.getText().toString();
		ed1.startAnimation(animout);
        ed2.startAnimation(animout);
        tv1.startAnimation(animout);
        tv2.startAnimation(animout);
        bt1.startAnimation(animout);
        setContentView(R.layout.page1);
        iv2 = (ImageView) findViewById(R.id.imageViewexp);
        iv3 = (ImageView) findViewById(R.id.imageViewmp);
		iv2.setVisibility(View.VISIBLE);
        iv3.setVisibility(View.VISIBLE);
        iv2.startAnimation(animfade);
        iv3.startAnimation(animfade);
	}
	public void explore(View v)
	{
		Intent i = new Intent(this,Tt.class);
		i.putExtra("abc", x);
		startActivity(i);
		
		//Toast.makeText(this, "asdas", Toast.LENGTH_LONG).show();
	}
	public void maps(View v)
	{
		setContentView(R.layout.floors);
		ivb1 = (ImageView) findViewById(R.id.imageView3);
		ivb2 = (ImageView) findViewById(R.id.ImageView05);
		ivb3 = (ImageView) findViewById(R.id.ImageView01);
		ivb4 = (ImageView) findViewById(R.id.ImageView04);
		ivb5 = (ImageView) findViewById(R.id.ImageView02);
		ivb6 = (ImageView) findViewById(R.id.ImageView03);
		ivb7 = (ImageView) findViewById(R.id.ImageView08);
		ivb8 = (ImageView) findViewById(R.id.ImageView07);
		ivbb = (ImageView) findViewById(R.id.ImageView09);
		ivbl = (ImageView) findViewById(R.id.ImageView10);
		animmove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
		animmove.setAnimationListener(this);
        // start the animation
		ivb1.setVisibility(View.VISIBLE);
        ivb1.startAnimation(animmove);
        
        ivb2.setVisibility(View.VISIBLE);
        ivb2.startAnimation(animmove);
        ivb3.setVisibility(View.VISIBLE);
        ivb3.startAnimation(animmove);
        ivb4.setVisibility(View.VISIBLE);
        ivb4.startAnimation(animmove);
        ivb5.setVisibility(View.VISIBLE);
        ivb5.startAnimation(animmove);
        ivb6.setVisibility(View.VISIBLE);
        ivb6.startAnimation(animmove);
        ivb7.setVisibility(View.VISIBLE);
        ivb7.startAnimation(animmove);
        ivb8.setVisibility(View.VISIBLE);
        ivb8.startAnimation(animmove);
        ivbb.setVisibility(View.VISIBLE);
        ivbb.startAnimation(animmove);
        ivbl.setVisibility(View.VISIBLE);
        ivbl.startAnimation(animmove);
    }
	
	public void floor1(View v)
	{
		setContentView(R.layout.floor1);
		iv6 = (ImageView) findViewById(R.id.imageView6);
		iv6.setOnTouchListener(new Touch());
		
	}
	public void floor2(View v)
	{
		setContentView(R.layout.floor2);
		ivtwo = (ImageView) findViewById(R.id.imageViewtwo);
		ivtwo.setOnTouchListener(new Touch());
		
	}
	public void floor3(View v)
	{
		setContentView(R.layout.floor3);
		ivthree = (ImageView) findViewById(R.id.imageViewthree);
		ivthree.setOnTouchListener(new Touch());
		
	}
	public void floor4(View v)
	{
		setContentView(R.layout.floor4);
		ivfour = (ImageView) findViewById(R.id.imageViewfour);
		ivfour.setOnTouchListener(new Touch());
		
	}
	public void floor5(View v)
	{
		setContentView(R.layout.floor5);
		ivfive = (ImageView) findViewById(R.id.imageViewfive);
		ivfive.setOnTouchListener(new Touch());
		
	}
	public void floor6(View v)
	{
		setContentView(R.layout.floor6);
		ivsix = (ImageView) findViewById(R.id.imageViewsix);
		ivsix.setOnTouchListener(new Touch());
		
	}
	public void floor7(View v)
	{
		setContentView(R.layout.floor7);
		ivseven = (ImageView) findViewById(R.id.imageViewseven);
		ivseven.setOnTouchListener(new Touch());
		
	}
	public void floor8(View v)
	{
		setContentView(R.layout.floor8);
		iveight = (ImageView) findViewById(R.id.imageVieweight);
		iveight.setOnTouchListener(new Touch());
		
	}
	public void base(View v)
	{
		setContentView(R.layout.floorb);
		ivb = (ImageView) findViewById(R.id.imageViewb);
		ivb.setOnTouchListener(new Touch());
		
	}
	public void lobby(View v)
	{
		//basement ka image hai abhi bhi
		setContentView(R.layout.floorl);
		ivl = (ImageView) findViewById(R.id.imageViewl);
		ivl.setOnTouchListener(new Touch());
		
	}
	public void locate(View v)
	{
		setContentView(R.layout.locate);

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
 
            new DownloadWebpageTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                processJson(object);
                	}
            }).execute("https://spreadsheets.google.com/tq?key=1ODz78OaSigq7DGdd2wkFyrSiB9MDwq3DnSqBs3xw0uI");
	}
	
	public void search(View v)
	{
		EditText emt = (EditText)findViewById(R.id.editTextsearch);
		if(!emt.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Please Enter a Valid Search", 2000).show();
			
		}
		else{
			for (int i=teams.size();i>0;i++)
				{
				Team a = teams.get(i);
				String s = a.position;
				int m = s.indexOf(97);
					if( m == -1)
					{
					
					}
					else
					{
					s = s.substring(0,m);
					if(a.position.equals(s))
					{
						setContentView(R.layout.mapper);	
						String prr = a.name;
						prr = prr.substring(0,m);
						String prm = a.wins;
						prm = prm.substring(0,m);
						String prw = a.draws;
						prw = prw.substring(0,m);
						
					}
					else
					{
						
					}
					}
				}
			}
		
		
        

	}
	

	
	
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}