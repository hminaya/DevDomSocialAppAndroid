package com.hminaya.devdomlib;

import java.util.List;

import com.hminaya.models.Event;
import com.hminaya.storage.EventRepository;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends ListActivity  {

	List<Event> events;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {

		try {
		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.event_main);
	    	
		    events = new EventRepository().getEvents();
		    
		    setListAdapter(new CustomAdapter());
		
			} catch (Exception e) {
				Toast.makeText(EventActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			}

	    }
	    
	
    class CustomAdapter extends ArrayAdapter<Event> {
    	CustomAdapter(){
    		super(EventActivity.this, R.layout.event_row, R.id.label, events);
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		if(convertView == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			convertView = inflater.inflate(R.layout.event_row, parent, false);
    		}
    		
    		Event evInfo = events.get(position);
			
			TextView label = (TextView)convertView.findViewById(R.id.label);
			label.setText(evInfo.getName());
			
			
			
    		return convertView;
    	}
    }
    
    
	
}
