package testovi;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import stan.Stan;
import stan.AgencijaProdajeStanova;

@RunWith(Parameterized.class)
public class AgencijaProdajeStanovaPronadjiStanParametrizedTest {
    private Stan stan;
    private AgencijaProdajeStanova agencija;

    @BeforeClass
    public static void ProveriOperativniSistem() {
        Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
    }

    @Rule
    public final TestRule timeout = Timeout.seconds(5);

    public AgencijaProdajeStanovaPronadjiStanParametrizedTest(Stan stan) {
        this.stan = stan;
    }

    @Parameters
    public static Collection<Object[]> stanovi() {
        return Arrays.asList(new Object[][] {
            {new Stan("Adresa1", 50, 1000)},
            {new Stan("Adresa1", 60, 1200)},
            {new Stan("Adresa2", 70, 1500)},
            {new Stan("Adresa3", 80, 1800)}
        });
    }

    @Before
    public void init() {
        agencija = new AgencijaProdajeStanova();
    }

    @Test
    public void pronadjiStanTest() {
        String adresa = null;
        assertNull(AgencijaProdajeStanova.pronadjiStan(adresa));
    }

    @Test
    public void pronadjiStanTest2() {
        assertFalse(AgencijaProdajeStanova.stan.contains(stan));
        AgencijaProdajeStanova.dodajStan(stan);
        LinkedList<Stan> stanovi = new LinkedList<Stan>();
        stanovi.add(stan);
        assertEquals(stanovi, AgencijaProdajeStanova.pronadjiStan(stan.getAdresa()));
    }

    @Test
    public void pronadjiStanTest3() {
        assertFalse(AgencijaProdajeStanova.stan.contains(stan));
        AgencijaProdajeStanova.dodajStan(stan);
        LinkedList<Stan> stanovi = new LinkedList<Stan>();
        stanovi.add(stan);
        assertNotEquals(stanovi, AgencijaProdajeStanova.pronadjiStan("Nepostojeca Adresa"));
    }

    @After
    public void destroy() {
        AgencijaProdajeStanova.stan.clear();
    }
}