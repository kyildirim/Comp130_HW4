package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import main.HW4;

public class Raid0Tests {

	@BeforeClass
	public static void initialize() {
		assertNotNull("[ERROR] Failed to initialize test image.", AutoGrader.testImage);
	}
	
	@Test
	public void testDisks(){
		HW4 MainClass = new HW4();
		MainClass.createRaid0Discs(AutoGrader.testImage);
		assertFalse(MainClass.disc1.equals(MainClass.disc2));
	}

}
