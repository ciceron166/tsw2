package testovi;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import stan.Stan;

public class StanTest {
	
	public Stan stan;
	
	@Rule
	public final TestRule timeout = Timeout.seconds(5);
	
	@Before
	public void ProveriOS() {
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@Rule
	public final ErrorCollector ec = new ErrorCollector();
	
	@Rule
	public final TestName name = new TestName(); 

	@Before
	public void testStan() {
		stan = new Stan("test adresa",500,650);
	}

	@Test
	public void testGetKvdratura() {
		int ocekivaniRez = 500;
		int stvarniRez = stan.getKvdratura();
		assertEquals(ocekivaniRez,stvarniRez);
	}

	/*
	 * @Test public void testSetKvadratura() { stan.setKvadratura(50); int
	 * ocekivaniRez = 50; assertEquals(ocekivaniRez,stan.getKvdratura());
	 * 
	 * try { stan.setKvadratura(-10);
	 * fail("Expected a RuntimeException to be thrown"); }catch (RuntimeException e)
	 * { assertEquals("Kvadratura ne sme biti manja od 0", e.getMessage()); } }
	 */

	@Test
	public void testGetCenaPoKvadratu() {
		int ocekivaniRez = 650;
		int stvarniRez = stan.getCenaPoKvadratu();
		assertEquals(ocekivaniRez,stvarniRez);
	}
	
	
	@Test() //anotacija za izuzetke
	public void setCena() {
		int ocekivaniRezultat = 650;
		int stvarniRezultat = stan.getCenaPoKvadratu();
		assertEquals(ocekivaniRezultat, stvarniRezultat);
		
		try {
		stan.setCenaPoKvadratu(-10);
		}catch(RuntimeException e) {
			assertEquals("Cena mora biti veca od 0 i manja od 5000",e.getMessage());
		}
	}
	
	@Test() //anotacija za izuzetke
	public void setCena2() {
		int ocekivaniRezultat = 650;
		int stvarniRezultat = stan.getCenaPoKvadratu();
		assertEquals(ocekivaniRezultat, stvarniRezultat);

		try {
		stan.setCenaPoKvadratu(5500);
		}catch(RuntimeException e) {
			assertEquals("Cena mora biti veca od 0 i manja od 5000",e.getMessage());
		}
	}
	
	@Test
	public void setCena3() {
		int ocekivaniRezultat = 650;
		int stvarniRezultat = stan.getCenaPoKvadratu();
		try {
			assertEquals(ocekivaniRezultat, stvarniRezultat);
		} catch (Throwable t) {
			ec.addError(t);
		}

		stan.setCenaPoKvadratu(500);
		ocekivaniRezultat = 500;
		stvarniRezultat = stan.getCenaPoKvadratu();
		try {
			assertEquals(ocekivaniRezultat, stvarniRezultat);
		} catch (Throwable t) {
			ec.addError(t);
		}

	}
	
	@Test
	public void testSetCenaPoKvadratuValid() {
	    stan.setCenaPoKvadratu(1000);
	    assertEquals(1000, stan.getCenaPoKvadratu());
	}

	
	@Test
	public void testIzracunajCenuVariousValues() {
	    stan.setKvadratura(100);
	    stan.setCenaPoKvadratu(1000);
	    assertEquals(100000, stan.izracunajCenu(), 0.001);

	    stan.setKvadratura(50);
	    stan.setCenaPoKvadratu(500);
	    assertEquals(25000, stan.izracunajCenu(), 0.001);
	}


	@Test
	public void testGetAdresa() {
		String ockR = "test adresa";
		String stvR = stan.getAdresa();
		assertEquals(ockR,stvR);
		
	}

	@Test
	public void testSetAdresa() {
		stan.setAdresa("kess");
		String ockR = "kess";
		String stvR = stan.getAdresa();
		assertEquals(ockR,stvR);
		
	}
	
	@Test
	public void testSetAdresaNull() {
	    try {
	        stan.setAdresa(null);
	        fail("Očekivan RuntimeException, ali nije bačen");
	    } catch (RuntimeException e) {
	        assertEquals("Morate uneti adresu", e.getMessage());
	    }
	}
	

	@Test
	public void testIzracunajCenu() {
		double ocekivaniR = (stan.getKvdratura() * stan.getCenaPoKvadratu());
		double stvarniR = stan.izracunajCenu();
		assertEquals(ocekivaniR,stvarniR, 0.001);
	}
	@Test
	public void testPovoljanStan2() {
		stan.setKvadratura(50);
		stan.setCenaPoKvadratu(65);
		assertTrue(stan.povoljanStan());
	}
	@Test
	public void testPovoljanStan() {
		assertFalse(stan.povoljanStan());
	}
	


	@Test
	public void testToString() {
		String ocekivaniR = "Stan [adresa=" + stan.getAdresa() + ", kvadratura=" + stan.getKvdratura() + ", cena po kvadratu=" + stan.getCenaPoKvadratu() + "]";
		String stvarniR = stan.toString();
		assertEquals(ocekivaniR,stvarniR);
	}
	
	@Test
	public void testSetCenaPoKvadratu() {
	    // Test valid value
	    stan.setCenaPoKvadratu(1000);
	    assertEquals(1000, stan.getCenaPoKvadratu());

	    // Test lower boundary
	    stan.setCenaPoKvadratu(1);
	    assertEquals(1, stan.getCenaPoKvadratu());

	    // Test upper boundary
	    stan.setCenaPoKvadratu(4999);
	    assertEquals(4999, stan.getCenaPoKvadratu());
	}

	
	@Test
	public void testSetKvadratura() {
	    // Test valid value
	    stan.setKvadratura(50);
	    assertEquals(50, stan.getKvdratura());

	    // Test zero (if allowed)
	    stan.setKvadratura(0);
	    assertEquals(0, stan.getKvdratura());

	    // Test negative value
	    try {
	        stan.setKvadratura(-10);
	        fail("Expected a RuntimeException to be thrown");
	    } catch (RuntimeException e) {
	        assertEquals("Kvadratura ne sme biti manja od 0", e.getMessage());
	    }

	    // Test large value (if there's no upper limit)
	    stan.setKvadratura(1000000);
	    assertEquals(1000000, stan.getKvdratura());
	}

}
