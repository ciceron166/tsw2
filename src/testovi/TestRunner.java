package testovi;

import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(StanTests.class, AGParametrizedTests.class);
		Logger l = Logger.getLogger(TestRunner.class.toString());
		
		for (Failure f: result.getFailures()) {
			l.warning(f.toString());
		}
		
		l.info("Vreme izvrsavanja:" + result.getRunTime());
		l.info("Broj testova:" + result.getRunCount());
		l.info("Uspesno testova:" + (result.getRunCount()-result.getIgnoreCount()-result.getAssumptionFailureCount()));
		l.info("Broj palih testova" + result.getFailureCount());
		
		
		if(result.wasSuccessful()) {
			l.info("Svi testovi su uspesno izvrseni");
		}else
			l.info("Postoje greske u testovima");
	}

}
