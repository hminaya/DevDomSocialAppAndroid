package com.hminaya.storage;

import java.util.List;

import com.hminaya.models.Category;

public class CategoryRepository {
	
	public List<Category> getCategories(){

		this.LoadInfo();
		
		return MemRepository.categorias;
	}
	
	public void LoadInfo(){
		
		if (MemRepository.categorias == null) {
			MemRepository.getInfoFromAPI();
		}
		
	}

}
