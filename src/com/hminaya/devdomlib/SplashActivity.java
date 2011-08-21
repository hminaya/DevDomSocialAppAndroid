package com.hminaya.devdomlib;

import com.hminaya.storage.CategoryRepository;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;


public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        new DownloadInfo().execute("Ejecutar");
        
    }
    protected class DownloadInfo extends AsyncTask<String, Integer, Long> {

        protected void onProgressUpdate(Integer... progress) {
            
        }

        protected void onPostExecute(Long result) {
        	try {
        		
            	Intent i = new Intent();
                i.setClass(SplashActivity.this, DevDomLibActivity.class);
                startActivity(i);
                finish();
                
    		} catch (Exception e) {
    			Log.e("DevDom_DownloadInfo_onPostExecute", "Error: " + e.toString() ) ;
    			finish();
    		}
            
        }

    	@Override
    	protected Long doInBackground(String... arg0) {

    		CategoryRepository Repcat = new CategoryRepository();
    		Repcat.LoadInfo();
    		
    		return (long) 0;
    	}
    }
}
