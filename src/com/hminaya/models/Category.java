package com.hminaya.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Category {

	public Category(int id, String categoryName){
		
		setCategoryName(categoryName);
		setId(id);
		
		tutorials = new ArrayList<Tutorial>();
		
	}
	
	private int id;
	private String categoryName;
	private List<Tutorial> tutorials;
	private String description;
	private Bitmap image;
	private String imageUrl;
	
	public void setImage(Bitmap image){
		this.image = image;
	}
	
	public Bitmap getImage(){
		return this.image;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	
	public void addTutorial(Tutorial tutorial){
		this.tutorials.add(tutorial);
	}
	
	public List<Tutorial> getTutorials(){
		return tutorials;
	}
	
	public void setTutorials(List<Tutorial> tutorials){
		this.tutorials = tutorials;
	}
	
}
