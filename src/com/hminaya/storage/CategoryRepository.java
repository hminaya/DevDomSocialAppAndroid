package com.hminaya.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.hminaya.devdomlib.R;
import com.hminaya.models.CategoryInfo;
import com.hminaya.models.Mem;
import com.hminaya.models.TutorialInfo;

public class CategoryRepository {
	
	public List<CategoryInfo> getCategories(){

		this.LoadInfo();
		
		return Mem.categorias;
	}
	
	public void LoadInfo(){
		
		if (Mem.categorias == null) {
			Mem.categorias = getCategoriesFromAPI();
		}
		
	}
	
 	public List<CategoryInfo> getCategoriesFromAPI(){
	
			List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
			
			JSONObject json = CategoryRepository.getJSONfromURL("http://js.developers.do/js/devdom.js");
			
				try{
					JSONArray categorias = json.getJSONArray("categorias");
				
				  	///Buscar las categorias
				    for(int i=0;i < categorias.length();i++){						
				
				    	JSONObject e = categorias.getJSONObject(i);
				    	
				    	/// Info general de la categoria
				    	CategoryInfo catInfo = new CategoryInfo(i, e.getString("categoryName") );
				    	catInfo.setDescription(e.getString("description"));
				    	catInfo.setImageResourceId(R.drawable.verde);
				    	catInfo.setImageUrl(e.getString("imageUrl"));
				    	catInfo.setImage(CategoryRepository.downloadFile(catInfo.getImageUrl()));
				    	
				    	try {
				    		/// Buscar los tutoriales dentro de cada categoria
					    	JSONArray ArrTutoriales = e.getJSONArray("tutorials");
					    	
					    	for (int j = 0; j < ArrTutoriales.length(); j++) {
					    		
					    		JSONObject t = ArrTutoriales.getJSONObject(j);
					    		
					    		String name= t.getString("name");
					    		String tutorialUrl = t.getString("tutorialUrl");
					    		String description = t.getString("description");
	
					    		catInfo.addTutorial(new TutorialInfo(name, tutorialUrl, description));
					    	}
					    	
						} catch (JSONException ex2) {
							Log.e("log_tag_getCategories_tuts", "Error parsing data: " + ex2.toString()) ;
						}
				    	
				    	/// Agregar todas las categorias
				    	categories.add(catInfo);
				}
		       }catch(JSONException ex) {
		       	 Log.e("DevDom_CategoryRepository_getCategories", "Error parsing data: " + ex.toString());
		       }
	
			return categories;
			
		}
		
	public static JSONObject getJSONfromURL(String url){
	
			//initialize
			InputStream is = null;
			String result = "";
			JSONObject jArray = null;
	
			//http post
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url);
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
	
			}catch(Exception e){
				Log.e("DevDom_CategoryRepository_JSONObject", "Error in http connection "+e.toString());
			}
	
			//convert response to string
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}catch(Exception e){
				Log.e("DevDom_CategoryRepository_JSONObject", "Error converting result "+e.toString());
			}
	
			//try parse the string to a JSON object
			try{
		        	jArray = new JSONObject(result);
			}catch(JSONException e){
				Log.e("DevDom_CategoryRepository_JSONObject", "Error parsing data " + e.toString() + " ||| " + result);
			}
	
			return jArray;
		}
	
	public static void downloadFile(String fileUrl, ImageView view){
       
		URL myFileUrl = null;          
        try {
             myFileUrl= new URL(fileUrl);
        } catch (MalformedURLException e) {
             e.printStackTrace();
        }
        
        try {
             HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
             
             conn.setDoInput(true);
             conn.connect();
             
             //int length = conn.getContentLength();
             InputStream is = conn.getInputStream();
             
             Bitmap bmImg = BitmapFactory.decodeStream(is);

             view.setImageBitmap(bmImg);
        } catch (IOException e) {
             Log.e("DevDom_CategoryRepository_downloadFile", "Error: " + e.toString());
        }
   }
	public static Bitmap downloadFile(String fileUrl){
		URL myFileUrl = null;          
        try {
             myFileUrl= new URL(fileUrl);
        } catch (MalformedURLException e) {
             e.printStackTrace();
        }
        
        try {
             HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
             
             conn.setDoInput(true);
             conn.connect();
             
             //int length = conn.getContentLength();
             InputStream is = conn.getInputStream();
             
             Bitmap bmImg = BitmapFactory.decodeStream(is);

             return bmImg;
        } catch (IOException e) {
             Log.e("DevDom_CategoryRepository_downloadFile", "Error: " + e.toString());
        }
		return null;
	}
}
