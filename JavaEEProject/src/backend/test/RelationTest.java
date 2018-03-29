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
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("FRA", "BOM",flugzeit,distanz));
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("FRA", "PAD",flugzeit,distanz));
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("PAD", "FRA",flugzeit,distanz));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "PAD",flugzeit,distanz));
		assertEquals(ErrorHandler.ZIELORTNICHTGEFUNDEN, myBean.createRelation("FRA", "+++",flugzeit,distanz));
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
