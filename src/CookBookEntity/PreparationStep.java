package CookBookEntity;

public class PreparationStep {
	private double StepsID;
	private String Description;
	private double Order;
	
	public PreparationStep(){
		
	}
	public PreparationStep(String description) {
		this.Description = description ; 
	}
	
	public PreparationStep(double stepsID, String description) {
		this.StepsID = stepsID;
		this.Description = description;
	}
	
	public double getStepsID() {
		return StepsID;
	}
	
	public void setStepsID(double stepsID) {
		StepsID = stepsID;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
	public double getOrder() {
		return Order;
	}

	public void setOrder(double order) {
		Order = order;
	}
	
	public String toString() {
		String string  =  new String();
		string = this.Order +" :"+ this.Description+" \n";
		return string;
	}
	
}