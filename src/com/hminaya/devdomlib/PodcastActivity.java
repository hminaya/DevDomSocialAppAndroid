package com.hminaya.devdomlib;

import java.util.List;

import com.hminaya.models.Podcast;
import com.hminaya.storage.PodcastRepository;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PodcastActivity extends ListActivity  {

	List<Podcast> podcasts;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {

		try {
		
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.podcast);
	    	
		    podcasts = new PodcastRepository().getPodcasts();
		    
		    setListAdapter(new CustomAdapter());
		
			} catch (Exception e) {
				Toast.makeText(PodcastActivity.this, e.toString(), Toast.LENGTH_LONG).show();
			}

	    }
	    
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id){
    	try {
    		
    		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.podcasts.get(position).getUrl()));
    		startActivity(browserIntent);
    		
    		
		} catch (Exception e) {
			Toast.makeText(PodcastActivity.this, e.toString(), Toast.LENGTH_LONG).show();
		}
    }
    
    
    class CustomAdapter extends ArrayAdapter<Podcast> {
    	CustomAdapter(){
    		super(PodcastActivity.this, R.layout.podcast_row, R.id.label, podcasts);
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		if(convertView == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			convertView = inflater.inflate(R.layout.podcast_row, parent, false);
    		}
    		
    		Podcast podInfo = podcasts.get(position);
			
			TextView label = (TextView)convertView.findViewById(R.id.label);
			label.setText(podInfo.getName());
			
			ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
			icon.setImageBitmap(podInfo.getLogo());
			
    		return convertView;
    	}
    }
    
    
}
