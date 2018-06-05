package DigitalCookbook;

public class Ingredient {
	private String ingredientname;
	private double amount ;
	private String unit ; 
	public String getIngredientname() {
		return ingredientname;
	}
	public void setIngredientname(String ingredientname) {
		this.ingredientname = ingredientname;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private String description; 
	
	public Ingredient(String ingredientname, double amount, String unit){
		this.ingredientname=ingredientname; 
		this.amount = amount ; 
		this.unit = unit; 	
	}
	public Ingredient(String ingredientname, double amount , String unit, String description){
		this.ingredientname = ingredientname; 
		this.amount = amount ; 
		this.unit = unit; 
		this.description = description ; 
		
	}
	
	public String toString() {
		String Tostring  =  new String();
		Tostring = this.ingredientname +" "+ String.valueOf(this.amount)+" "+ this.unit+ " " + this.description+"\n";
		
		
		return Tostring;
	}
}