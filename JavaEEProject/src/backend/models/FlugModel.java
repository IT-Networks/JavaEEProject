package backend.models;

import java.util.List;

public class FlugModel {
	
	private String start;
	private String goal;
	private String food;
	private String name;
	private List<String> buchungen;
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal; 
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(List<String> buchungen) {
		this.buchungen = buchungen;
	}
	
	public FlugModel(String start,String goal,String food, String name, List<String> buchungen)
	{
		this.start = start;
		this.goal = goal;
		this.food = food;
		this.name = name;
		this.buchungen = buchungen;
	}

}
