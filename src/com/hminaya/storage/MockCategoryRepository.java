package com.hminaya.storage;

import java.util.ArrayList;
import java.util.List;

import com.hminaya.devdomlib.R;
import com.hminaya.models.CategoryInfo;
import com.hminaya.models.TutorialInfo;


public class MockCategoryRepository {
	
	public List<CategoryInfo> getCategories(){
		
		List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
		
		CategoryInfo jQuery = new CategoryInfo(0, "jQuery");
		jQuery.setImageResourceId(R.drawable.verde);
		jQuery.setDescription("Conviertete en un Ninja de jQuery");
		
		jQuery.addTutorial(new TutorialInfo("jQuery 123", "http://developers.do", "Algo un chin mas largo"));
		jQuery.addTutorial(new TutorialInfo("jQuery 124", "http://developers.do", "Algo un chin mas largo"));
		jQuery.addTutorial(new TutorialInfo("jQuery 125", "http://developers.do", "Algo un chin mas largo"));
		
		categories.add(jQuery);
		
		CategoryInfo java = new CategoryInfo(1, "Java");
		java.setImageResourceId(R.drawable.verde);
		java.setDescription("Do you want a Coffee?");
		
		java.addTutorial(new TutorialInfo("Java 121", "http://developers.do", "Algo un chin mas largo"));
		java.addTutorial(new TutorialInfo("Java 122", "http://developers.do", "Algo un chin mas largo"));
		java.addTutorial(new TutorialInfo("Java 123", "http://developers.do", "Algo un chin mas largo"));
		java.addTutorial(new TutorialInfo("Java 124", "http://developers.do", "Algo un chin mas largo"));
		
		categories.add(java);

		CategoryInfo mvc = new CategoryInfo(2, "MVC");
		mvc.setImageResourceId(R.drawable.verde);
		mvc.setDescription("Do you want a Coffee?");
		
		mvc.addTutorial(new TutorialInfo("mvc 121", "http://developers.do", "Algo un chin mas largo"));
		mvc.addTutorial(new TutorialInfo("mvc 122", "http://developers.do", "Algo un chin mas largo"));
		mvc.addTutorial(new TutorialInfo("mvc 123", "http://developers.do", "Algo un chin mas largo"));
		mvc.addTutorial(new TutorialInfo("mvc 124", "http://developers.do", "Algo un chin mas largo"));
		
		categories.add(mvc);

		return categories;
	}
	
	

}
