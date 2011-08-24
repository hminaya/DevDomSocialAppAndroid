package com.hminaya.devdomlib;

import java.util.List;

import com.hminaya.models.OpcionInfo;
import com.hminaya.storage.OptionRepository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	List<OpcionInfo> opciones;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        this.opciones = new OptionRepository().getOpciones();
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	Intent i = new Intent();
                
            	switch (position) {
				case 0:
				
					i.setClass(HomeActivity.this, DevDomLibActivity.class);					
	                startActivity(i);
	                
	                break;
				case 4:
	                
					i.setClass(HomeActivity.this, ColaboradoresActivity.class);					
	                startActivity(i);
	                
					break;
				default:
					break;
				}
                
            }
        });

    }
    
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return opciones.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
        
        	View myView = convertView;
        	
        	if(convertView == null)
        	{
        		LayoutInflater li = getLayoutInflater();
        		myView = li.inflate(R.layout.home_row, null);
        		
        		TextView tv = (TextView)myView.findViewById(R.id.opcion);
        		tv.setText(opciones.get(position).getName());
        		
        		ImageView iv = (ImageView)myView.findViewById(R.id.icon);
        		iv.setImageResource(opciones.get(position).getImageResource());
        		
        	}
        	
        	return myView;

        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.icon_news,  R.drawable.icon_calendar
        };
    }
    
}
