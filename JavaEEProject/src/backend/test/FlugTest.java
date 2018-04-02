package backend.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.FlugHandler;
import backend.enterpriseLogic.SuccessHandler;

public class FlugTest {
	private FlugHandler myBean = new FlugHandler();

	@Test
	public void testGetFlugStatus() throws ParseException {

		System.out.println(myBean.getFlugStatus("Tue Apr 17 17:46:00 CEST 2018",
				"MH1/4: Abflug: 2018-04-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00€)"));
		System.out.println(myBean.getFlugStatus("Sun Apr 01 17:46:00 CEST 2018",
				"MH1/4: Abflug: 2018-04-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00€)"));
		System.out.println(myBean.getFlugStatus("Sun Apr 01 10:00:00 CEST 2018",
				"MH1/4: Abflug: 2018-04-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00€)"));

	}

	@Test
	public void testcreateFlug() throws ParseException {
		assertEquals(SuccessHandler.FLUGANLAGE, myBean.createFlug("Tue Apr 17 17:46:00 CEST 2018",
				"5. Relation: Startort: FRA, Zielort: BOM (1500 km, 10:30:00 Stunden)", 25.0));
	}

	public void testGetAllFlugzeugs() {
		List<String> relationenliste = new ArrayList<String>();
		relationenliste = myBean.getAllRelationen();
		for (String str : relationenliste) {
			System.out.println(str);
		}
	}

	@Test
	public void testGetAllFluege() {
		List<String> flugliste = new ArrayList<String>();
		flugliste = myBean.getAllFluege();
		for (String str : flugliste) {
			System.out.println(str);
		}
	}

}
