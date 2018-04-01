package backend.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.PassagierHandler;
import backend.enterpriseLogic.SuccessHandler;

public class PassagierTest {
	private PassagierHandler myBean = new PassagierHandler();

//	public void testCreatePassagier() {
//		assertEquals(SuccessHandler.PASSAGIERANLAGE,
//				myBean.createPassagier("Halil", "Özdogan", "Am Stockhof 2, 31785 Hameln", "08.09.1995", "deutsch"));
//	}

	@Test
	public void testCreatePassagierNegative() {
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG,
				myBean.createPassagier("", "Özdogan", "Am Stockhof 2, 31785 Hameln", "08.09.1995", "deutsch"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG,
				myBean.createPassagier("Halil", "", "Am Stockhof 2, 31785 Hameln", "08.09.1995", "deutsch"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG,
				myBean.createPassagier("Halil", "Özdogan", "", "08.09.1995", "deutsch"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG,
				myBean.createPassagier("Halil", "Özdogan", "Am Stockhof 2, 31785 Hameln", "", "deutsch"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG,
				myBean.createPassagier("Halil", "Özdogan", "Am Stockhof 2, 31785 Hameln", "08.09.1995", ""));
	}

	@Test
	public void testGetAllPassagiere() {
		List<String> passagierliste = new ArrayList<String>();
		passagierliste = myBean.getAllPassagiere();
		for (String str : passagierliste) {
			System.out.println(str);
		}
	}

}
