package com.hminaya.models;

import java.util.ArrayList;
import java.util.List;

public class CategoryInfo {

	public CategoryInfo(int id, String categoryName){
		
		setCategoryName(categoryName);
		setId(id);
		
		tutorials = new ArrayList<TutorialInfo>();
		
	}
	
	private int id;
	private String categoryName;
	private List<TutorialInfo> tutorials;
	private String description;
	private int imageResourceId;
	
	private String imageUrl;
	
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
	
	public void setImageResourceId(int imageResourceId){
		this.imageResourceId = imageResourceId;
	}
	
	public int getImageResourceId(){
		return imageResourceId;
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
	
	public void addTutorial(TutorialInfo tutorial){
		this.tutorials.add(tutorial);
	}
	
	public List<TutorialInfo> getTutorials(){
		return tutorials;
	}
	
	public void setTutorials(List<TutorialInfo> tutorials){
		this.tutorials = tutorials;
	}
	
}
