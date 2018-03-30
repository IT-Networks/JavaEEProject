package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.FlugHandler;

public class FlugTest {
	private FlugHandler myBean = new FlugHandler();

	@Test
	public void testcreateFlug() {
		Date abflug = new Date();
		Date ankunft = new Date();
		assertEquals("", myBean.createFlug(abflug, ankunft, 1, 25.0));
	}
	
	@Test
	public void testGetAllFlugzeugs() {
		List<String> relationenliste = new ArrayList<String>();
		relationenliste = myBean.getAllRelationen();
		for(String str : relationenliste) {
			System.out.println(str);
		}
	}

}
