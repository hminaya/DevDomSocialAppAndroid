package com.hminaya.devdomlib;

import com.hminaya.models.CategoryInfo;
import com.hminaya.models.Mem;
import com.hminaya.models.TutorialInfo;
import com.hminaya.storage.CategoryRepository;

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

	private CategoryInfo categoria;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

	try {
	
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tutorial_main);
    	
    	if (Mem.categoria == null) {
    		Toast.makeText(TutorialListActivity.this, "Ha ocurrido un error, por favor cierre el App y vuelva a abrirlo", Toast.LENGTH_LONG).show();
    		
    		Mem.categorias = new CategoryRepository().getCategories();
    		Mem.categoria = Mem.categorias.get(1);
    		
		}
	            
	    categoria = Mem.categoria;
	    
	    ImageView icon = (ImageView)findViewById(R.id.icon);
	    icon.setImageResource(categoria.getImageResourceId());
	    CategoryRepository.downloadFile(categoria.getImageUrl(), icon);
	    
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

    
    class TutorialListAdapter extends ArrayAdapter<TutorialInfo>{
    	
    	TutorialListAdapter(){
    		super(TutorialListActivity.this, R.layout.tutorial_row, R.id.label, categoria.getTutorials());
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent){
    		
    		LayoutInflater inflater = getLayoutInflater();
    		View row = inflater.inflate(R.layout.tutorial_row, parent, false);
    		
    		TextView label=(TextView)row.findViewById(R.id.label);
    		label.setText(categoria.getTutorials().get(position).getName());
    		
    		TextView description=(TextView)row.findViewById(R.id.tutorial_description);
    		description.setText(categoria.getTutorials().get(position).getDescription());
    		
    		return(row);
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
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.inicio:
            
        	Intent iHome = new Intent();
        	iHome.setClass(TutorialListActivity.this, HomeActivity.class);
            startActivity(iHome); 
            finish();
        	
            return true;
        case R.id.Colaboradores:

        	Intent i = new Intent();
            i.setClass(TutorialListActivity.this, ColaboradoresActivity.class);
            startActivity(i);

            return true;
        default:
            //return super.onOptionsItemSelected(item);
        	return true;
        }
    }

	
}
