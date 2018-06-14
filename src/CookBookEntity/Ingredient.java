package CookBookEntity;

public class Ingredient {
	private double IngredientsID;
	private String Name;
	private double Amount;
	private String Unit;
	private String Description;
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Ingredient(){
		
	}
	
	public Ingredient(String name, double amount, String unit, String description ){
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
		this.Description = description;
	}
	
	public Ingredient(String name, double amount, String unit) {
		this.Name = name; 
		this.Amount = amount ; 
		this.Unit = unit ; 
		
	}
		
	public double getIngredientsID() {
		return IngredientsID;
	}
	
	public void setIngredientsID(double ingredientsID) {
		IngredientsID = ingredientsID;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public double getAmount() {
		return Amount;
	}
	
	public void setAmount(double amount) {
		Amount = amount;
	}
	
	public String getUnit() {
		return Unit;
	}
	
	public void setUnit(String unit) {
		Unit = unit;
	}
	
	public String toString() {
		String Tostring  =  new String();
		Tostring = this.Name +" "+ String.valueOf(this.Amount)+" "+ this.Unit +" ("+this.Description+") \n";		
		return Tostring;
	}		
	

}
