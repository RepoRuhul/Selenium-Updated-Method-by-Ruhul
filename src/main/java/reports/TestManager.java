package reports;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestManager {
	// HashMap is used when we use key value pair, no duplication, not ordered
	static HashMap<Long, ExtentTest> tests = new HashMap<>();

	// This method is ExtentTest type and method name is createTest()
	public static ExtentTest createTest(ExtentReports extentReports, String testName) {
		ExtentTest extentTest = extentReports.createTest(testName);
		tests.put(Thread.currentThread().getId(), extentTest);
		return extentTest;
	}

	public static ExtentTest getTest() {
		return tests.get(Thread.currentThread().getId());
	}

}
