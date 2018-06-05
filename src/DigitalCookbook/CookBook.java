package DigitalCookbook;
import java.util.LinkedList;
import java.util.*;
public class CookBook {
	private String cookbookname;
	private LinkedList<Recipe> recipelist;
	public void add(Recipe recipe) {
		recipelist.add(recipe);
	}
	
	public void showallrecipe() {
		Iterator<Recipe> it = recipelist.iterator();
		System.out.println("Show all recipes"); 
		while(it.hasNext()) {
			
			Recipe recipeitr = it.next();
			recipeitr.showrecipe();
		}
	}
	
	CookBook(String cookbookname){
		this.cookbookname = cookbookname; 
		recipelist = new LinkedList<Recipe>();
		
	}
	
	Recipe getRecipe(String recipename) {
		int index ; 
		index = recipelist.indexOf(new Recipe(recipename));
		return recipelist.get(index);
	}
	
	
	
	
}
