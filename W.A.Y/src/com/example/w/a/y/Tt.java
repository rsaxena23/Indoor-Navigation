package com.example.w.a.y;

import java.net.URLEncoder;
import android.widget.AbsoluteLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Tt extends Activity implements SensorEventListener {
	HttpRequest mReq = new HttpRequest();
	Context cc;

	TextView tv;
	int cnt;
	String work="Start";
	int samples=30;
    ListView lv;
    ListAdapter la;
    ImageView iv;
    Button bn;
    SensorManager sm;
    Sensor ss1;
    String x ,sex,aak ;
    String router[][]={{
    			"00:24:6c:b3:3e:40",
    			"00:24:6c:b3:1c:80",
    			"00:24:6c:b3:b1:e1",
    			"00:24:6c:b3:65:a0",
    			"00:24:6c:b3:4d:60" },
    			{"d8:c7:c8:2a:a2:30","00:24:6c:b3:87:a0","d8:c7:c8:2b:de:f0","d8:c7:c8:2a:a7:10","00:24:6c:b3:93:f0"},
    			{"d8:c7:c8:2a:9f:00",
    				"d8:c7:c8:2a:9c:f0",
    				"d8:c7:c8:2c:7f:30",
    				"d8:c7:c8:2b:dc:d0",
    				"d8:c7:c8:2c:8a:70",
    				"00:24:6c:b3:8c:10",
    				"d8:c7:c8:2a:a0:60"	
    			},
    			{"d8:c7:c8:2a:a1:50","d8:c7:c8:2c:81:90","d8:c7:c8:2c:83:00","d8:c7:c8:2c:88:50","d8:c7:c8:2c:81:50","00:24:6c:b3:3e:60","d8:c7:c8:2c:88:60"},
    			{"d8:c7:c8:2a:a0:c0","d8:c7:c8:2a:a0:40","d8:c7:c8:2c:7f:c0","d8:c7:c8:2b:2f:10","d8:c7:c8:2c:87:90","d8:c7:c8:2c:82:f0","d8:c7:c8:2c:83:b0","d8:c7:c8:2c:81:30","00:24:6c:b3:95:c2"},
    			{"d8:c7:c8:2c:83:20","d8:c7:c8:2c:81:20","d8:c7:c8:2a:9e:d0","d8:c7:c8:2c:7e:c0","d8:c7:c8:2c:8a:30","d8:c7:c8:2c:82:e0","d8:c7:c8:2a:9f:f0","00:24:6c:b3:4d:c0","00:24:6c:b3:4d:21"},
    			{"d8:c7:c8:2c:81:60","d8:c7:c8:2a:a6:70","d8:c7:c8:2b:ee:60","d8:c7:c8:2a:a1:c0","d8:c7:c8:2a:a1:e0","d8:c7:c8:2c:87:f0","00:24:6c:b3:4e:32","00:24:6c:b3:4d:e0"},
    			{"00:24:6c:b3:1c:50","00:24:6c:b3:4e:00","d8:c7:c8:2c:83:40","d8:c7:c8:2a:a2:d0","d8:c7:c8:2b:e5:50","d8:c7:c8:2c:7e:20","00:24:6c:b3:1c:00","d8:c7:c8:2a:a3:a0"},
    			{},
    			{"00:24:6c:b3:0f:30","d8:c7:c8:2c:76:50","d8:c7:c8:2c:83:30","d8:c7:c8:2c:7d:70","d8:c7:c8:2c:83:d0","d8:c7:c8:2c:76:00","d8:c7:c8:2c:89:70","d8:c7:c8:2b:e3:b0","00:24:6c:b2:ef:a0"
}
    		
    }; 
    int routcood[][][]={{{}},
    		{{}},
    		{{20,80},{20,70},{80,70},{90,80},{50,60},{50,10},{80,40}},
    		{{}},
    		{{}},
    		{{}},
    		{{}},
    	{{30,10},{55,65},{70,65},{30,65},{20,80},{70,80},{70,30},{80,80}},
    	{{}},
    	{{}}
    		
    };
    String freq="";
    AbsoluteLayout back;
	double wher[]=new double[2],x1,y1,dispx = 100 , dispy = 100;
	
	int noX,noY,stepX=100,stepY=100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tt);	
		Intent i = getIntent();
		x = i.getExtras().getString("abc");
		Toast.makeText(this, x, 2000).show();
		cc=getApplicationContext();
		tv=(TextView)findViewById(R.id.ttVw1);
		lv=(ListView)findViewById(R.id.wifiList1);
		bn=(Button)findViewById(R.id.buttonTp);
		iv=(ImageView)findViewById(R.id.loc);
		back=(AbsoluteLayout)findViewById(R.id.ttAbs);
		iv.setVisibility(ImageView.INVISIBLE);
		tv.setText(work);
	
		Display dis=getWindowManager().getDefaultDisplay();
		Point margi=new Point();
		dis.getSize(margi);
	
		noX=margi.x;
		noY=margi.y;
		

		
	//	Toast.makeText(cc," h:"+margi.x+" w:"+margi.y, Toast.LENGTH_SHORT).show();
		
	/*	sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		ss1=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		sm.registerListener(this,ss1,sm.SENSOR_DELAY_NORMAL);*/
		
	//	Toast.makeText(cc, "Start", Toast.LENGTH_LONG);
		/*int centres[][]={{40,10},{10,60},{80,60}};                         //=new int[3][2];		
		double minm[]={300,301,302};
		double va,vb;
		va=((Math.pow(minm[1], 2) - Math.pow(minm[2], 2))-(Math.pow(centres[1][0], 2) - Math.pow(centres[2][0], 2))-(Math.pow(centres[1][1], 2) - Math.pow(centres[2][1], 2)))/2;
		vb=((Math.pow(minm[1], 2) - Math.pow(minm[0], 2))-(Math.pow(centres[1][0], 2) - Math.pow(centres[0][0], 2))-(Math.pow(centres[1][1], 2) - Math.pow(centres[0][1], 2)))/2;
		double finy= ((vb*(centres[2][0] - centres[1][0]))-(va*(centres[0][0]-centres[1][0])))/(((centres[0][1]-centres[1][1])*(centres[2][0]-centres[1][0])) - ((centres[2][1]-centres[1][1])*(centres[0][0]-centres[1][0])));
		double finx=(va -(finy*(centres[2][1]-centres[1][1])))/(centres[2][0]-centres[1][0]);
		
		tv.setText("X:"+finx+" Y:"+finy);*/ 
		}
	
	public void getDispCoOd(double x,double y)
	{
		
	}
	
	public double calculateDistance(double levelInDb, double freqInMHz)    {
		   double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(levelInDb)) / 20.0;
		   return Math.pow(10.0, exp);
		}
	
	public void findr(ArrayList calc)
	{
		int floorno=0,backgroundRes=0;
		int cntrs[][]=new int[3][2];
		int locs[]={0,0,0};
		work="fin";
		double strs[]={Double.parseDouble(calc.get(1).toString()),Double.parseDouble(calc.get(4).toString()),Double.parseDouble(calc.get(7).toString())};
		//int floors[]={R.layout.floorb,R.layout.floor1,R.layout.floor2,R.layout.floor3,R.layout.floor4,R.layout.floor5,R.layout.floor6,R.layout.floor7,R.layout.floor8};
		int floorbg[]={R.drawable.floorb,R.drawable.floorone,R.drawable.floor2,R.drawable.floor3,R.drawable.floor4,R.drawable.floor5,R.drawable.floor6,R.drawable.floor7,R.drawable.floor8};
		
		int count=0;
		for(int i=0;i<7;i+=3)
		{
			for(int f=0;f<10;f++)
			{
			for(int j=0;j<router[f].length;j++)
			{
			if(calc.get(i).toString().equalsIgnoreCase(router[f][j]) && count<=2)
			{
				cntrs[count][0]=routcood[f][j][0];
				cntrs[count][1]=routcood[f][j][1];
				locs[count]=j+1;
				floorno=f+1;
				backgroundRes=floorbg[f];
				count++;
				break;
			}
			}
			}
		}
		
	//work=" "+count;	
		if(count==3)
wher=trilater(strs, cntrs);
		
if(!Double.isNaN(wher[0]) && !Double.isNaN(wher[1]) && !Double.isInfinite(wher[0]) && !Double.isInfinite(wher[1]) && !(wher[0]>100 || wher[1]>100))
{
	x1=wher[0];
	y1=wher[1];
			}
else
{
	x1=cntrs[0][0];
	y1=cntrs[0][1];
}

//back.setBackgroundResource(backgroundRes);
iv.setVisibility(ImageView.VISIBLE);
dispx=x1*((double)noX/(double)stepX);
dispy=y1*((double)noY/(double)stepY);
iv.setX((float)dispx);
iv.setY((float)dispy);
work=count+" X:"+wher[0]+" Y:"+wher[1]+" dispX:"+dispx+" dispY:"+dispy+"("+locs[0]+","+locs[1]+","+locs[2]+")";

Toast.makeText(cc, "("+locs[0]+","+locs[1]+","+locs[2]+")", Toast.LENGTH_SHORT).show();
//sex = Double.toString(dispx);
//sex = sex + "a";
//aak = Double.toString(dispy);
//aak = aak + "a";

Thread t = new Thread(new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		postdata();
		
		
	}
});
	t.start();

	}
	
	
	
	public void postdata()
	{
		try{
			String fullURL = "https://docs.google.com/forms/d/18cizYaQWnZgQIS1xPfaQgpeSCRTc0DOBP52RbMjMszQ/formResponse";
			String col1 = "71121a";
			String col2 = "100a";
			String col3 = "100a";
			String col4 = "1a";
			//Toast.makeText(this, "Posted", 2000).show();
			String data = "entry_236824544=" + URLEncoder.encode(col1) + "&" + "entry_1154286729=" + URLEncoder.encode(col2) + "&" + "entry_545923616=" + URLEncoder.encode(col3) + "&" + "entry_455914441=" + URLEncoder.encode(col4);
			String response = mReq.sendPost(fullURL, data);
			}
			catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), "Error", 2000).show();
			}
		}
	
	public void lets(View v)
	{
		Toast.makeText(cc, "Burron fine", Toast.LENGTH_SHORT);
		
		final Handler handler = new Handler();
	    Timer timer = new Timer();
	    TimerTask doAsynchronousTask = new TimerTask()
	    {       
	        @Override
	        public void run() {
	            handler.post(new Runnable() {
	                public void run() {       
	                    try
	                    {
	                    	new Hpf().execute();

	                    } 
	                    catch (Exception e) 
	                    {

	                    }
	                }
	            });
	        }
	    };	    
  timer.schedule(doAsynchronousTask, 0, 3000);		
	
		
	
	}
	
	public double[] trilater(double minm[],int centres[][])
	{
		double va,vb;
		va=((Math.pow(minm[1], 2) - Math.pow(minm[2], 2))-(Math.pow(centres[1][0], 2) - Math.pow(centres[2][0], 2))-(Math.pow(centres[1][1], 2) - Math.pow(centres[2][1], 2)))/2;
		vb=((Math.pow(minm[1], 2) - Math.pow(minm[0], 2))-(Math.pow(centres[1][0], 2) - Math.pow(centres[0][0], 2))-(Math.pow(centres[1][1], 2) - Math.pow(centres[0][1], 2)))/2;
		double finy= ((vb*(centres[2][0] - centres[1][0]))-(va*(centres[0][0]-centres[1][0])))/(((centres[0][1]-centres[1][1])*(centres[2][0]-centres[1][0])) - ((centres[2][1]-centres[1][1])*(centres[0][0]-centres[1][0])));
		double finx=(va -(finy*(centres[2][1]-centres[1][1])))/(centres[2][0]-centres[1][0]);
		double co_od[]={finx,finy};
	//	work+=" co_od X:"+co_od[0] +" Y:"+co_od[1];
		return co_od;
	}
	
	
	public double[] difftri(double minm[],int centres[][])
	{
		double co_od[]=new double[2];
		double x=0,y=0;
		
		y=(  (  Math.pow(minm[0], 2) - Math.pow(minm[1], 2) + Math.pow(centres[1][0], 2) + Math.pow(centres[1][1], 2) - Math.pow(centres[0][0], 2) - 
				Math.pow(centres[0][1], 2))*(centres[2][0] - centres[0][0] ) - 
				(Math.pow(minm[0], 2) - Math.pow(minm[2], 2) + Math.pow(centres[2][0], 2) + Math.pow(centres[2][1], 2) - Math.pow(centres[0][0], 2) - 
						Math.pow(centres[0][1], 2))*(centres[1][0] - centres[0][0]))/( ((centres[1][1] - centres[0][1])*(centres[2][0] - centres[0][0]))
								- ((centres[2][1] - centres[0][1])*(centres[1][0] - centres[0][0])) );
	work="Y:"+y;
		
		return co_od;
	}
	
	
	private class Hpf extends AsyncTask<Void, Void, Void>
	{
		ArrayList values=new ArrayList();
		ArrayList finaldis=new ArrayList();
		ArrayList calc=new ArrayList();
		
		String toasttxt="None";
		
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	       
	    }
		
		public void align()
		{
			//Toast.makeText(cc, "align", Toast.LENGTH_SHORT).show();
		//	work="Changed";
			double min,temp1,temp2;
			int i,j,pos;
			String mac;
			work="order";
			if(calc.size()>=3)
			{
		    for(i=1;i<calc.size();i+=3)
		    {
		    	min=Double.parseDouble(calc.get(i).toString());
		    	pos=i;
		    	for(j=(i+3);j<calc.size();j+=3)
		    	{
		    		if(Double.parseDouble(calc.get(j).toString())<min)
		    		{
		    		min=Double.parseDouble(calc.get(j).toString());	    		
		    	  pos=j;
		    		}
		    	}		
		    	if(pos!=i)
		    	{
		    		mac=calc.get(i-1).toString();
		    		temp1=Double.parseDouble(calc.get(i).toString());
		    		temp2=Double.parseDouble(calc.get(i+1).toString());
		    		
		    		calc.set(i-1, calc.get(pos-1).toString());
		    		calc.set(i, Double.parseDouble(calc.get(pos).toString()));
		    		calc.set(i+1, Double.parseDouble(calc.get(pos+1).toString()));
		    		
		    		calc.set(pos-1,mac);
		    		calc.set(pos,temp1);
		    		calc.set(pos+1,temp2);
		    	}
		    	
		    }
		    
	//	    delRest(calc);
		    findr(calc);
		    //work="A:"+calc.get(1)+" B:"+calc.get(4)+" C:"+calc.get(7);
			}
			else
			{
				work="not 3";
			}
			
		    //Toast.makeText(cc,"A:"+calc.get(1)+" B:"+calc.get(4)+" C:"+calc.get(7), Toast.LENGTH_LONG).show();
		} 
		
		public void delRest(ArrayList calc)
		{
			int found=0,dontdel=0;
			String temp;
			work="del";
		for(int runs4=0;runs4<=calc.size();runs4+=3)
		{
			temp=calc.get(runs4).toString();
			found=0;
			dontdel=0;
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<router[i].length;j++)
				{
				  if(temp.equalsIgnoreCase(router[i][j]))
				  {
					  found=1;
					  break;
				  }
				}
				if(found==1)
				{
					
					dontdel=1;
					break;
				}
			}
			if(dontdel==1)
			{
				calc.remove(runs4);
				calc.remove(runs4+1);
				calc.remove(runs4+2);
			}
		}
		
			findr(calc);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
		//	work="Project done";
			try
			{
						
		for(cnt=0;cnt<samples;cnt++)
		{
			WifiManager ab=(WifiManager)cc.getSystemService(WIFI_SERVICE);
			if(ab.getWifiState()!=ab.WIFI_STATE_ENABLED)
				ab.setWifiEnabled(true);
			
			ab.startScan();
			
			ArrayList<ScanResult> sr= new ArrayList<ScanResult>();
			sr=(ArrayList)ab.getScanResults();
			int size=sr.size();
			size--;
			while(size>=0)
			{
				ScanResult each=sr.get(size);
				if(each.SSID.equalsIgnoreCase("svkm-wifi"))
				{
				freq=each.frequency+"";
				if(cnt==0)
				{
		//	values.add(each.SSID+" Mac:"+each.BSSID+" dbm:"+each.level);
					values.add(each.SSID);
					values.add(each.BSSID);			
			values.add(each.level);
			values.add(calculateDistance(each.level, each.frequency));
			
			values.add(1);
			
			toasttxt="First";
				}
				else
				{
					toasttxt="Second";
					for(int coun=1;coun<values.size();coun+=5)
					{
			
						if(each.BSSID.equalsIgnoreCase(values.get(coun).toString()))
						{
					
							int sum_db=Integer.parseInt(values.get(coun+1).toString()) + each.level;
					
							double sum_strength=Double.parseDouble(values.get(coun+2).toString()) + calculateDistance(each.level,each.frequency);
					
							int new_count= Integer.parseInt(values.get(coun+3).toString()) +1;
					
							values.set(coun+1, sum_db);
					
						values.set(coun+2, sum_strength);
					
						values.set(coun+3,new_count);
					
						}
					}
				}
				}
			size--;
			}
		}
		for(int fn=0;fn<values.size();fn+=5)
		{
			double avg_db=(double) Double.parseDouble(values.get(fn+2).toString())/Integer.parseInt(values.get(fn+4).toString());
			double avg_strength= Double.parseDouble(values.get(fn+3).toString())/Integer.parseInt(values.get(fn+4).toString());
			finaldis.add(values.get(fn)+" Mac:"+values.get(fn+1)+" Avg Dbm:"+avg_db+" Str:"+avg_strength+" count:"+values.get(fn+4));
			calc.add(values.get(fn+1));			
			calc.add(avg_strength);
			calc.add(avg_db);
			//values.set(fn+2, avg_db);
			//values.set(fn+3, avg_strength);
		}
		
		align();
	//	double d1[]={40.5,55.2,58.2};
	//	int c1[][]={{20,10},{25,50},{50,20}};
	//  difftri(d1, c1);
	//  trilater(d1, c1);
		
			//tv.setText("Lets see");
			}catch(Exception e)
			{
				toasttxt=e.getMessage();
				//Toast.makeText(cc, toasttxt+" "+cnt+" "+e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			return null;
		}
		
		
		@Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);

	        //this method will be running on UI thread

	        tv.setText(work);
       la=new ArrayAdapter(cc,R.layout.data,calc);
			lv.setAdapter(la);
		
//			Toast.makeText(cc, freq, Toast.LENGTH_LONG).show();
			
	    }
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	//	tv.setText("X:"+event.values[0]+" Y:"+event.values[1]+" Z:"+event.values[2]);
	//	Toast.makeText(cc, "Worked", Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	



}

