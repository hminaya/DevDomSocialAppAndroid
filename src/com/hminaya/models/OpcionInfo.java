package com.hminaya.models;

public class OpcionInfo {

	private String name;
	private int imageResource;
	
	public OpcionInfo(String name, int imageResource)
	{
		setName(name);
		setImageResource(imageResource);
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setImageResource(int imageResource){
		this.imageResource = imageResource;
	}
	
	public int getImageResource(){
		return this.imageResource;
	}
	
}
