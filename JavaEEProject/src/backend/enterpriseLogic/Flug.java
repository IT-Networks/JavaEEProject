package backend.enterpriseLogic;

import java.util.List;

public class Flug {
	
	String start;
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
