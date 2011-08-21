package com.hminaya.models;

public class TutorialInfo {
	
	private int id;
	private String name;
	private String tutorialUrl;
	private String description;
	private String imageUrl;
	
	public TutorialInfo(String name, String tutorialUrl, String description){
		
		setName(name);
		setTutorialUrl(tutorialUrl);
		setDescription(description);
		
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setTutorialUrl(String tutorialUrl){
		this.tutorialUrl = tutorialUrl;
	}
	
	public String getTutorialUrl(){
		return this.tutorialUrl;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

}
