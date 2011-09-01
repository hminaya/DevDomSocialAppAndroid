package com.hminaya.devdomlib;

import com.hminaya.storage.MemRepository;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);
            
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            
            if(ni == null)
            {
            	Toast.makeText(SplashActivity.this, R.string.ErrConnInternet, Toast.LENGTH_LONG).show();
            	finish();
            }
           
            if(ni.isConnected())
        	{
            	new DownloadInfo().execute("Ejecutar");
        	}
            else
            {
            	Toast.makeText(SplashActivity.this, R.string.ErrConnInternet, Toast.LENGTH_LONG).show();
            	finish();
            }    		
    		
		} catch (Exception e) {
			Log.e("DevDom_onCreate", "Error: " + e.toString() ) ;
			finish();
		}

    }
    protected class DownloadInfo extends AsyncTask<String, Integer, Long> {

        protected void onProgressUpdate(Integer... progress) {
            
        }

        protected void onPostExecute(Long result) {
        	try {
        		
            	Intent i = new Intent();
                i.setClass(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
                
    		} catch (Exception e) {
    			Log.e("DevDom_DownloadInfo_onPostExecute", "Error: " + e.toString() ) ;
    			finish();
    		}
            
        }

    	@Override
    	protected Long doInBackground(String... arg0) {
    		
    		try {
        		MemRepository.getInfoFromAPI();
			} catch (Exception e) {
    			Log.e("DevDom_DownloadInfo_doInBackground", "Error: " + e.toString() ) ;
    			finish();
			}
    		
    		return (long) 0;
    	}
    }
}
