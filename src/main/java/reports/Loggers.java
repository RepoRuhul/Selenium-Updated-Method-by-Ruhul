package reports;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Loggers {
	// Why below line is static?
	// this is used inside static method, non static variable cannot be called inside static method
	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	// Why this method is static?
	// Then the class can call directly
	public static void logTheTest(String msg) {
		logger.log(Level.INFO, msg); // it helps to print in console
		Reporter.log(msg + "<br>"); // for testng report as index.html
		TestManager.getTest().log(Status.INFO, msg); // for extent report as extent_report.html
	}

}
