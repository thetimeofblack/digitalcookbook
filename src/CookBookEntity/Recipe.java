package CookBookEntity;

import java.util.*;

public class Recipe {
	
	private String RecipeID;
	private String Name;
	private String Description;
	private int Privacy;
	private int ServeNumber;
	private int PrepareTime;
	private int CookTime;
	private String Category;
	private LinkedList<PreparationStep> PreparationSteps;
	private LinkedList<Ingredient> ingredientlist;
	private int Rate =0 ;
	private String Comments;
	private boolean favourite =false;
	
	public Recipe(String Name, String Category , int ServeNumber){
		this.Name  = Name ; 
		this.Category = Category; 
		this.ServeNumber = ServeNumber; 
		ingredientlist = new LinkedList<Ingredient>();
		PreparationSteps = new LinkedList<PreparationStep>();
		
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
	
	public LinkedList<PreparationStep> getPreparationSteps() {
		return PreparationSteps;
	}

	public void setPreparationSteps(LinkedList<PreparationStep> preparationSteps) {
		this.PreparationSteps = preparationSteps;
	}

	public LinkedList<Ingredient> getIngredientlist() {
		return ingredientlist;
	}

	public void setIngredientlist(LinkedList<Ingredient> ingredientlist) {
		this.ingredientlist = ingredientlist;
	}
	
	public String getRecipeID() {
		return RecipeID;
	}

	public void setRecipeID(String recipeid) {
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
		PreparationStep preparationSteps = new PreparationStep(steps);
		this.PreparationSteps.add(preparationSteps);
	}
	public void addIngredient(Ingredient ingredient) {
		
		this.ingredientlist.add(ingredient);
	}
	
	public void setPreparationTime (int PreparationTime) {
		this.PrepareTime = PreparationTime;
	}
	public void setCookingTime(int cookTime) {
		this.CookTime = cookTime ; 
	}
	public void addPreparationStep(PreparationStep preparationstep) {
		this.PreparationSteps.add(preparationstep);
	}
	
	public void show() {
		System.out.println("RecipeID: "+this.RecipeID);
		System.out.println("RecipeName: "+this.Name);
		System.out.println("RecipeCategory: "+this.Category);
		System.out.println("RecipeDescription: "+this.Description);
		System.out.println("RecipePrivacy: "+this.Privacy);
		System.out.println("PreparationTime: "+this.PrepareTime);
		System.out.println("RecipeNumber: "+this.ServeNumber);
		System.out.println("RecipeCookTime: "+this.CookTime);
	 
		Iterator<Ingredient> itri = this.ingredientlist.iterator(); 
		while(itri.hasNext()) {
			System.out.print(itri.next().toString());
		}
		Iterator<PreparationStep> itrp = this.PreparationSteps.iterator();
		while(itrp.hasNext()) {
			System.out.print(itrp.next().toString());
		}
	}
	public void setFavourite(boolean favourite) {
		this.favourite = favourite ; 
	}
}
