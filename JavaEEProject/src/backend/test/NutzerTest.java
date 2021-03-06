package backend.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import backend.enterpriseLogic.ErrorHandler;
import backend.enterpriseLogic.NutzerHandler;

public class NutzerTest {
	private NutzerHandler myBean = new NutzerHandler();
	@Test
	public void testCreateNutzer() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Halil", "�zdogan", "Halil2", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Halil", "�zdogan", "Halil", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Dagobert", "Duck", "dagobert", "start123", "Mitarbeiter"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createNutzer("", "�zdogan", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "", "start123", "Manager"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "user", "", "Manager"));
		assertEquals(ErrorHandler.DATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "�zdogan", "user", "start123", ""));
	}
	@Test
	public void testCheckPasswort() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		assertEquals("Login erfolgreich.", myBean.checkPasswort("Halil", "start123"));
		assertEquals(ErrorHandler.NUTZERNICHTGEFUNDEN, myBean.checkPasswort("Halillllll", "start123"));
		assertEquals(ErrorHandler.NUTZERPASSWORTFALSCH, myBean.checkPasswort("Halil", "falsch"));
		
	}
	@Test
	public void testgetNutzertyp() {
		assertEquals("Manager", myBean.getNutzertyp("Halil"));
		assertEquals("Mitarbeiter", myBean.getNutzertyp("dagobert"));
	}

}
