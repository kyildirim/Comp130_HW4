package test;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;

public class Raid10Tests {

	@BeforeClass
	public static void initialize() {
		assertNotNull("[ERROR] Failed to initialize test image.", AutoGrader.testImage);
		assertNotNull("[ERROR] Failed to initialize ErrorCollector", AutoGrader.errorCollector);
	}
	
}
