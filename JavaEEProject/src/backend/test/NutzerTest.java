package backend.test;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.NutzerHandler;

public class NutzerTest {
	private NutzerHandler myBean = new NutzerHandler();
	@Test
	public void testCreateNutzer() {
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Halil", "�zdogan", "halil", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Dagobert", "Duck", "dagobert", "start123", "Mitarbeiter"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("", "�zdogan", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "user", "", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "user", "start123", ""));
	}

}
