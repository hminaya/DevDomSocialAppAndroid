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
import com.hminaya.models.Category;
import com.hminaya.models.Community;
import com.hminaya.models.Option;
import com.hminaya.models.Tutorial;

public class MemRepository {
	
	public static Category categoria;
	public static List<Category> categorias;
	
	public static Option opcion;
	public static List<Option> opciones;
	
	public static Community comunidad;
	public static List<Community> comunidades;
	
	
	public static void getInfoFromAPI(){
		
		/// Buscar el JSON
		JSONObject json = MemRepository.getJSONfromURL("http://js.developers.do/js/devdom.js");

		/// Cargar las opciones
		List<Option> opciones = new ArrayList<Option>();
		
		opciones.add(new Option("Tutoriales", R.drawable.icon_tutorial));
		opciones.add(new Option("Eventos", R.drawable.icon_calendar));
		opciones.add(new Option("Noticias", R.drawable.icon_news));
		opciones.add(new Option("Empleos", R.drawable.icon_empleos));
		opciones.add(new Option("Colaboradores", R.drawable.icon_colaboradores));
		opciones.add(new Option("Comunidades", R.drawable.icon_community));
		
		MemRepository.opciones = opciones;
		
		/// Cargar las comunidades
		List<Community> communities = new ArrayList<Community>();
		try{
			JSONArray comunidades = json.getJSONArray("comunidades");

		    for(int i=0;i < comunidades.length();i++){						
		
		    	JSONObject e = comunidades.getJSONObject(i);
		    	
		    	Community comInfo = new Community();
		    	comInfo.setName(e.getString("name"));
		    	comInfo.setDescription(e.getString("description"));
		    	comInfo.setTwitter(e.getString("twitter"));
		    	comInfo.setLogoUrl(e.getString("logo"));
		    	comInfo.setUrl(e.getString("url"));
		    	comInfo.setLogo(MemRepository.downloadFile(comInfo.getLogoUrl()));

		    	communities.add(comInfo);
		}
       }catch(Exception ex) {
       	 Log.e("DevDom_MemRepository_getInfoFromAPI_communities", ex.toString());
       }
		MemRepository.comunidades = communities;
		
		/// Cargar las Categorias
		List<Category> categories = new ArrayList<Category>();

		try{
				JSONArray categorias = json.getJSONArray("categorias");
			
			  	///Buscar las categorias
			    for(int i=0;i < categorias.length();i++){						
			
			    	JSONObject e = categorias.getJSONObject(i);
			    	
			    	/// Info general de la categoria
			    	Category catInfo = new Category(i, e.getString("categoryName") );
			    	catInfo.setDescription(e.getString("description"));
			    	catInfo.setImageUrl(e.getString("imageUrl"));
			    	catInfo.setImage(MemRepository.downloadFile(catInfo.getImageUrl()));
			    	
			    	try {
			    		/// Buscar los tutoriales dentro de cada categoria
				    	JSONArray ArrTutoriales = e.getJSONArray("tutorials");
				    	
				    	for (int j = 0; j < ArrTutoriales.length(); j++) {
				    		
				    		JSONObject t = ArrTutoriales.getJSONObject(j);
				    		
				    		String name= t.getString("name");
				    		String tutorialUrl = t.getString("tutorialUrl");
				    		String description = t.getString("description");

				    		catInfo.addTutorial(new Tutorial(name, tutorialUrl, description));
				    	}
				    	
					} catch (JSONException ex2) {
						Log.e("DevDom_MemRepository_getInfoFromAPI_categories", ex2.toString()) ;
					}
			    	
			    	/// Agregar todas las categorias
			    	categories.add(catInfo);
			}
	       }catch(Exception ex) {
	       	 Log.e("DevDom_MemRepository_getInfoFromAPI_categories", ex.toString());
	       }

		MemRepository.categorias = categories;
		
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
				Log.e("DevDom_MemRepository_JSONObject", "Error in http connection "+e.toString());
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
				Log.e("DevDom_MemRepository_JSONObject", "Error converting result "+e.toString());
			}
	
			//try parse the string to a JSON object
			try{
		        	jArray = new JSONObject(result);
			}catch(Exception e){
				Log.e("DevDom_MemRepository_JSONObject", "Error parsing data " + e.toString() + " ||| " + result);
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
