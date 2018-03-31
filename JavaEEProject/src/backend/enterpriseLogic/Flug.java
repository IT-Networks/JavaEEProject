package backend.enterpriseLogic;

import java.util.List;

public class Flug {
	
	String start;
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

	public List<String> getPassagner() {
		return passagner;
	}

	public void setPassagner(List<String> passagner) {
		this.passagner = passagner;
	}

	String goal;
	String food;
	String name;
	List<String> passagner;
	
	public Flug(String start,String goal,String food, String name, List<String> passagner)
	{
		this.start = start;
		this.goal = goal;
		this.food = food;
		this.name = name;
		this.passagner = passagner;
	}

}
