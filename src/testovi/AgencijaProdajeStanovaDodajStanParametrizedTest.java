package testovi;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

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
public class AgencijaProdajeStanovaDodajStanParametrizedTest {

    private Stan stan;
    private AgencijaProdajeStanova agencija;

    @BeforeClass
    public static void ProveriOperativniSistem() {
        Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
    }

    @Rule
    public final TestRule timeout = Timeout.seconds(5);

    public AgencijaProdajeStanovaDodajStanParametrizedTest(Stan stan) {
        this.stan = stan;
    }

    @Parameters
    public static Collection<Object[]> stanovi() {
        return Arrays.asList(new Object[][] {
            {new Stan("Example Address", 100, 2000)},
            {new Stan("Example Address2", 55, 500)},
            {new Stan("Example Address3", 24, 200)},
            {new Stan("Example Address4", 10, 20)}
        });
    }

    @Before
    public void init() {
        agencija = new AgencijaProdajeStanova();
    }

    @Test(expected = NullPointerException.class)
    public void dodajStanTest() {
        stan = null;
        agencija.dodajStan(stan);
    }

    @Test(expected = RuntimeException.class)
    public void dodajStanTest2() {
        agencija.dodajStan(stan);
        agencija.dodajStan(stan);
    }

    @Test
    public void dodajStanTest3() {
        assertFalse(agencija.stan.contains(stan));
        agencija.dodajStan(stan);
        assertTrue(agencija.stan.contains(stan));
    }

    @After
    public void destroy() {
        agencija.stan.clear();
        agencija = null;
    }
}