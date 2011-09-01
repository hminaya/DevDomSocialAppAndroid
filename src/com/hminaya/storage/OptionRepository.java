package com.hminaya.storage;

import java.util.List;

import com.hminaya.models.Option;

public class OptionRepository {
	
	public void LoadInfo(){
		
		if (MemRepository.opciones == null) {
			MemRepository.getInfoFromAPI();
		}
	}
	
	public List<Option> getOpciones(){
		
		this.LoadInfo();
		
		return MemRepository.opciones;
	}
}
