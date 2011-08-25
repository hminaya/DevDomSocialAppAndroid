package com.hminaya.devdomlib;

import java.util.List;

import com.hminaya.models.CategoryInfo;
import com.hminaya.models.Mem;
import com.hminaya.storage.CategoryRepository;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DevDomLibActivity extends ListActivity {
    
	List<CategoryInfo> categories;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        categories = new CategoryRepository().getCategories();
        
        setListAdapter(new CategoryAdapter());

    }
    
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id){
    	    	
    	Mem.categoria = categories.get(position);
    	
    	try {
        	Intent i = new Intent(DevDomLibActivity.this, TutorialListActivity.class);	
        	startActivity(i);	
    		
		} catch (Exception e) {
			Toast.makeText(DevDomLibActivity.this, e.toString(), Toast.LENGTH_LONG).show();
		}

    };
    
    class CategoryAdapter extends ArrayAdapter<CategoryInfo> {
    	CategoryAdapter(){
    		super(DevDomLibActivity.this, R.layout.category_row, R.id.label, categories);
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		if(convertView == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			convertView = inflater.inflate(R.layout.category_row, parent, false);
    		}
    		
    		CategoryInfo catInfo = categories.get(position);
			
			TextView label = (TextView)convertView.findViewById(R.id.label);
			label.setText(catInfo.getCategoryName());
			
			ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
			icon.setImageBitmap(catInfo.getImage());
			
			TextView detalle = (TextView)convertView.findViewById(R.id.details_line);
			String description = catInfo.getDescription();
			detalle.setText(description == null ? "" : description);
    		
    		return convertView;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	Intent i = new Intent();
    	
        switch (item.getItemId()) {
        case R.id.inicio:

        	// No es necesario lanzar el Activity, ya que este viene del Home y si le dan
        	// a back vuelve al Home
        	//i.setClass(DevDomLibActivity.this, HomeActivity.class);
            //startActivity(i); 	
            finish();
        	
            return true;
        case R.id.Colaboradores:

            i.setClass(DevDomLibActivity.this, ColaboradoresActivity.class);
            

            return true;
        default:

        	return true;
        }
    }

}