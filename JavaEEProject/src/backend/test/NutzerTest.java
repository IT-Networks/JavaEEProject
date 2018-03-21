package backend.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.NutzerHandler;

class NutzerTest {
	private NutzerHandler myBean = new NutzerHandler();
	@Test
	void testCreateNutzer() {
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Halil", "Özdogan", "halil", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Dagobert", "Duck", "dagobert", "start123", "Mitarbeiter"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("", "Özdogan", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "user", "", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "user", "start123", ""));
	}

}
