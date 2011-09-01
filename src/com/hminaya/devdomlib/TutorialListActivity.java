package com.hminaya.devdomlib;

import com.hminaya.models.Category;
import com.hminaya.models.Tutorial;
import com.hminaya.storage.CategoryRepository;
import com.hminaya.storage.MemRepository;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TutorialListActivity extends ListActivity  {

	private Category categoria;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

	try {
	
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tutorial_main);
    	
    	if (MemRepository.categoria == null) {
    		Toast.makeText(TutorialListActivity.this, R.string.ErrGeneral, Toast.LENGTH_LONG).show();
    		
    		MemRepository.categorias = new CategoryRepository().getCategories();
    		MemRepository.categoria = MemRepository.categorias.get(1);
    		
		}
	            
	    categoria = MemRepository.categoria;
	    
	    ImageView icon = (ImageView)findViewById(R.id.icon);
	    icon.setImageBitmap(categoria.getImage());
	    
	    TextView tv = (TextView)findViewById(R.id.label);
	    tv.setText(categoria.getCategoryName());
	    
	    setListAdapter(new TutorialListAdapter());
	    
	    ListView lv = getListView();

	    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	        //@Override
	        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
	            return onLongListItemClick(v,pos,id);
	        }
	    });

		
		} catch (Exception e) {
			Toast.makeText(TutorialListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
		}

    }
    
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id){
    	try {
    		
    		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.categoria.getTutorials().get(position).getTutorialUrl()));
    		startActivity(browserIntent);
    		
    		
		} catch (Exception e) {
			Toast.makeText(TutorialListActivity.this, e.toString(), Toast.LENGTH_LONG).show();
		}
    }

    protected boolean onLongListItemClick(View v, int pos, long id) {
        
    	String texto = this.categoria.getTutorials().get(pos).getName() + " | " + 
    			this.categoria.getTutorials().get(pos).getTutorialUrl();
    	
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "#DevDom - ");
        i.putExtra(Intent.EXTRA_TEXT, texto);
        startActivity(Intent.createChooser(i, "Compartir tutorial"));
        
        return true;
    }

    
    class TutorialListAdapter extends ArrayAdapter<Tutorial>{
    	
    	TutorialListAdapter(){
    		super(TutorialListActivity.this, R.layout.tutorial_row, R.id.label, categoria.getTutorials());
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		if(convertView == null)
    		{
    			LayoutInflater inflater = getLayoutInflater();
    			convertView = inflater.inflate(R.layout.tutorial_row, parent, false);
    		}
    		
    		Tutorial tutInfo = categoria.getTutorials().get(position);
    		
    		TextView label=(TextView)convertView.findViewById(R.id.label);
    		label.setText(tutInfo.getName());
    		
    		TextView description=(TextView)convertView.findViewById(R.id.tutorial_description);
    		description.setText(tutInfo.getDescription());
    		
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
            
        	i.setClass(TutorialListActivity.this, HomeActivity.class);
            startActivity(i); 
            finish();
        	
            return true;
        case R.id.Colaboradores:

            i.setClass(TutorialListActivity.this, ColaboradoresActivity.class);
            startActivity(i);

            return true;
        default:

        	return true;
        }
    }

	
}
