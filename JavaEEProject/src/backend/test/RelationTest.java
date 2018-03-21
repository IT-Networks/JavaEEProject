package backend.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.naming.NamingException;


import org.junit.jupiter.api.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.RelationHandler;

class RelationTest {
	private RelationHandler myBean = new RelationHandler();


	@Test
	void testCreateRelation() throws NamingException {
		assertEquals(ErrorHandler.RELATIONSCHONVORHANDEN, myBean.createRelation("FRA", "PAD"));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "PAD"));
		assertEquals(ErrorHandler.ZIELORTNICHTGEFUNDEN, myBean.createRelation("FRA", "+++"));
		assertEquals(ErrorHandler.STARTORTNICHTGEFUNDEN, myBean.createRelation("+++", "+++"));
		
	}

}
