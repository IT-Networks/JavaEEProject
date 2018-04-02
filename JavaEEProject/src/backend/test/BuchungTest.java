package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.BuchungHandler;
import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.SuccessHandler;

public class BuchungTest {
	private BuchungHandler myBean = new BuchungHandler();

//	@Test
//	 public void testCreateBuchung() {
//	 assertEquals(SuccessHandler.BUCHUNGANLAGE, myBean.createBuchung(
//	 "1. Passagier: Halil Özdogan (Anschrift: Am Stockhof 2, 31785 Hameln, Geburtsdatum: 08.09.1995, Nationalitaet: deutsch)",
//	 "MH1/4: Abflug: 2018-14-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00€)"));
//	 }

	@Test
	public void testCreateBuchungNegative() {
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createBuchung("",
				"MH1/4: Abflug: 2018-14-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00 €)"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createBuchung(
				"1. Passagier: Halil Özdogan (Anschrift: Am Stockhof 2, 31785 Hameln, Geburtsdatum: 08.09.1995, Nationalitaet: deutsch)",
				""));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createBuchung("", ""));
	}

	@Test
	public void testCalculateCapacity() {
		System.out.println(myBean
				.calculateCapacity("MH1/4: Abflug: 2018-14-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00 €)"));
	}

	@Test
	public void testGetBuchungbyFlug() {
		List<String> buchungsliste = new ArrayList<String>();
		buchungsliste = myBean
				.getBuchungenbyFlug("MH1/4: Abflug: 2018-14-01 23:14, Ankunft: 2018-14-01 23:14 (Preis: 25.00 €)");
		for (String str : buchungsliste) {
			System.out.println(str);
		}
	}

	@Test
	public void testGetBuchungbyPassagier() {
		List<String> buchungsliste = new ArrayList<String>();
		buchungsliste = myBean.getBuchungenbyPassagier(
				"1. Passagier: Halil Özdogan (Anschrift: Am Stockhof 2, 31785 Hameln, Geburtsdatum: 08.09.1995, Nationalitaet: deutsch)");
		for (String str : buchungsliste) {
			System.out.println(str);
		}
	}

}
