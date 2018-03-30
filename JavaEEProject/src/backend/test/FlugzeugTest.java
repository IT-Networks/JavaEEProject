package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.FlugzeugHandler;

public class FlugzeugTest {
	private FlugzeugHandler myBean = new FlugzeugHandler();
	@Test
	public void testCreateFlugzeug() {
		String hersteller = null;
		String typ = null;
		assertEquals("Erfolgreiche Anlage des Flugzeugs!", myBean.createFlugzeug("Airbus", "A380-800", 853));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createFlugzeug(hersteller, "A380-800", 853));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createFlugzeug("Airbus", typ, 853));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createFlugzeug("", "A380-800", 853));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createFlugzeug("Airbus", "", 853));
	}
	@Test
	public void testGetAllFlugzeugs() {
		List<String> flugzeugliste = new ArrayList<String>();
		flugzeugliste = myBean.getAllFlugzeuge();
		for(String str : flugzeugliste) {
			System.out.println(str);
		}
	}

}
