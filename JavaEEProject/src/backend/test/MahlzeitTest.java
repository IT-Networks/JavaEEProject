package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.MahlzeitHandler;
import backend.enterpriseLogic.SuccessHandler;

public class MahlzeitTest {
	private MahlzeitHandler myBean = new MahlzeitHandler();

	@Test
	public void testCreateMahlzeit() {
		assertEquals(ErrorHandler.MAHLZEITSCHONVORHANDEN, myBean.createMahlzeit("Schnitzel mit Pommes", "Deftiges", false));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createMahlzeit("Schnitzel mit Pommes", "", false));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createMahlzeit("", "Deftiges", false));
	}
	
	@Test
	public void testGetAllMahlzeiten() {
		List<String> mahlzeitliste = new ArrayList<String>();
		mahlzeitliste = myBean.getAllMahlzeiten();
		for(String str : mahlzeitliste) {
			System.out.println(str);
		}
	}
	@Test
	public void testAssignMahlzeitToFlug() {
		assertEquals(SuccessHandler.MAHLZEITZUORDNUNG, myBean.assignMahlzeitToFlug("1. Mahlzeit: Pizza Margarita (Teigwaren, vegetarisch: ja)", "MH1/6: Abflug: 2018-26-01 01:26, Ankunft: 2018-56-01 11:56 (Preis: 25.00 €)"));
	}

}
