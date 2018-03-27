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
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Halil", "Özdogan", "halil", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERSCHONVORHANDEN, myBean.createNutzer("Dagobert", "Duck", "dagobert", "start123", "Mitarbeiter"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("", "Özdogan", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "", "user", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "", "start123", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "user", "", "Manager"));
		assertEquals(ErrorHandler.NUTZERDATENUNVOLLSTAENDIG, myBean.createNutzer("Halil", "Özdogan", "user", "start123", ""));
	}
	@Test
	public void testCheckPasswort() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		assertEquals("Login erfolgreich.", myBean.checkPasswort("testuser", "start123"));
		assertEquals(ErrorHandler.NUTZERNICHTGEFUNDEN, myBean.checkPasswort("testuserrrrrr", "start123"));
		assertEquals(ErrorHandler.NUTZERPASSWORTFALSCH, myBean.checkPasswort("testuser", "falsch"));
		
	}

}
