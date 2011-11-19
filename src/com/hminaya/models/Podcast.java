package com.hminaya.models;

import android.graphics.Bitmap;

public class Podcast {

	private int id;
	private String name;
	private String description;
	private String url;
	private String twitter;
	private String logoUrl;
	private Bitmap logo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public Bitmap getLogo() {
		return logo;
	}
	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}
	
}
