package com.hminaya.storage;

import java.util.List;

import com.hminaya.models.Podcast;

public class PodcastRepository {

	public List<Podcast> getPodcasts(){
		this.LoadInfo();
		
		return MemRepository.podcasts;
	}
	
	public void LoadInfo(){
		
		if (MemRepository.podcasts == null) {
			MemRepository.getInfoFromAPI();
		}
		
	}
	
}
