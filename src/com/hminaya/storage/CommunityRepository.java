package com.hminaya.storage;

import java.util.List;

import com.hminaya.models.Community;

public class CommunityRepository {

	
	public List<Community> getCommunities(){
		this.LoadInfo();
		
		return MemRepository.comunidades;
	}
	
	public void LoadInfo(){
		
		if (MemRepository.comunidades == null) {
			MemRepository.getInfoFromAPI();
		}
		
	}
}
