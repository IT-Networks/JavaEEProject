package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.RelationHandler;

public class RelationTest {
	private RelationHandler myBean = new RelationHandler();


	@Test
	public void testCreateRelation() throws NamingException {
		String flugzeit = "10:30:00";
		int distanz = 1500;
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("Madrid-Barajas", "Chhatrapati Shivaji International Airport",flugzeit,distanz));
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("Flughafen Frankfurt am Main", "Flughafen Paderborn/Lippstadt",flugzeit,distanz));
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("Flughafen Paderborn/Lippstadt", "Flughafen Frankfurt am Main",flugzeit,distanz));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "Flughafen Paderborn/Lippstadt",flugzeit,distanz));
		assertEquals(ErrorHandler.ZIELORTNICHTGEFUNDEN, myBean.createRelation("Flughafen Frankfurt am Main", "+++",flugzeit,distanz));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "+++",flugzeit,distanz));
		
	}
	
	@Test
	public void testgetAllFlughafennamen() {
		List<String> flughafennamen = new ArrayList<String>();
		flughafennamen = myBean.getAllFlughafennamen();
		for(String str : flughafennamen) {
			System.out.println(str);
		}
	}
	

}
