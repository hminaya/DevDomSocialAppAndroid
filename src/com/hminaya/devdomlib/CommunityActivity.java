package com.hminaya.devdomlib;

import java.util.List;

import com.hminaya.models.Community;
import com.hminaya.storage.CommunityRepository;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CommunityActivity extends ListActivity  {

	List<Community> communities;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {

		try {
		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.community_main);
	    	
		    communities = new CommunityRepository().getCommunities();
		    
		    setListAdapter(new CustomAdapter());
		
			} catch (Exception e) {
				Toast.makeText(CommunityActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			}

	    }
	    
	
    class CustomAdapter extends ArrayAdapter<Community> {
    	CustomAdapter(){
    		super(CommunityActivity.this, R.layout.community_row, R.id.label, communities);
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		if(convertView == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			convertView = inflater.inflate(R.layout.community_row, parent, false);
    		}
    		
    		Community comInfo = communities.get(position);
			
			TextView label = (TextView)convertView.findViewById(R.id.label);
			label.setText(comInfo.getName());
			
			ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
			icon.setImageBitmap(comInfo.getLogo());
			
    		return convertView;
    	}
    }
    
    
}
