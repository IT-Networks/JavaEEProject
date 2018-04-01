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
	public void testcreateFlug() throws ParseException {
		assertEquals(SuccessHandler.FLUGANLAGE, myBean.createFlug("2018-04-01 01:26", "1. Relation: Startort: FRA, Zielort: BOM (1500 km, 10:30:00 Stunden)", 25.0));
	}
	

	public void testGetAllFlugzeugs() {
		List<String> relationenliste = new ArrayList<String>();
		relationenliste = myBean.getAllRelationen();
		for(String str : relationenliste) {
			System.out.println(str);
		}
	}
	@Test
	public void testGetAllFluege() {
		List<String> flugliste = new ArrayList<String>();
		flugliste = myBean.getAllFluege();
		for(String str : flugliste) {
			System.out.println(str);
		}
	}
//	@Test
//	public void testCalculateTime() {
//		Date date = new Date();
//		Time time = Time.valueOf("01:30:00");
//		Date newtime = myBean.calculateAnkunftszeit(date, time);
//		System.out.println(newtime.toString());
//	}

}
