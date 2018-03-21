package backend.test;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.RelationHandler;

public class RelationTest {
	private RelationHandler myBean = new RelationHandler();


	@Test
	public void testCreateRelation() throws NamingException {
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("FRA", "PAD"));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "PAD"));
		assertEquals(ErrorHandler.ZIELORTNICHTGEFUNDEN, myBean.createRelation("FRA", "+++"));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "+++"));
		
	}

}
