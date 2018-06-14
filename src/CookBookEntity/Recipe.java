package CookBookEntity;

import java.util.*;

public class Recipe {
	
	private int RecipeID;
	private String Name;
	private String Description;
	private int Privacy;
	private int ServeNumber;
	private int PrepareTime;
	private int CookTime;
	private String Category;
	private LinkedList<PreparationSteps> PreparationSteps;
	private LinkedList<Ingredient> ingredientlist;
	private int Rate;
	private String Comments;
	
	public Recipe(String Name, String Category , int ServeNumber){
		this.Name  = Name ; 
		this.Category = Category; 
		this.ServeNumber = ServeNumber; 
		
	}
	
	public Recipe() {
		
	}

	
	public int getRate() {
		return Rate;
	}

	public void setRate(int rate) {
		Rate = rate;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		this.Comments = comments;
	}
	
	public LinkedList<PreparationSteps> getPreparationSteps() {
		return PreparationSteps;
	}

	public void setPreparationSteps(LinkedList<PreparationSteps> preparationSteps) {
		this.PreparationSteps = preparationSteps;
	}

	public LinkedList<Ingredient> getIngredientlist() {
		return ingredientlist;
	}

	public void setIngredientlist(LinkedList<Ingredient> ingredientlist) {
		this.ingredientlist = ingredientlist;
	}
	
	public int getRecipeID() {
		return RecipeID;
	}

	public void setRecipeID(int recipeid) {
		RecipeID = recipeid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getPrivacy() {
		return Privacy;
	}

	public void setPrivacy(int privacy) {
		Privacy = privacy;
	}

	public int getServeNumber() {
		return ServeNumber;
	}

	public void setServeNumber(int serveNumber) {
		ServeNumber = serveNumber;
	}

	public int getPrepareTime() {
		return PrepareTime;
	}

	public void setPrepareTime(int prepareTime) {
		PrepareTime = prepareTime;
	}

	public int getCookTime() {
		return CookTime;
	}

	public void setCookTime(int cookTime) {
		CookTime = cookTime;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}	
	public void addPreparationStep(String steps) {
		PreparationSteps preparationSteps = new PreparationSteps(steps);
		this.PreparationSteps.add(preparationSteps);
	}
	public void addIngredient(Ingredient ingredient) {
		this.ingredientlist.add(ingredient);
	}
	
}
