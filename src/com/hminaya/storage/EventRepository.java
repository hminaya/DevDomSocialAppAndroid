package com.hminaya.storage;

import java.util.List;

import com.hminaya.models.Event;

public class EventRepository {

	
	public List<Event> getEvents(){
		this.LoadInfo();
		
		return MemRepository.eventos;
	}
	
	public void LoadInfo(){
		if (MemRepository.eventos == null) {
			MemRepository.getInfoFromAPI();
		}
}
}
