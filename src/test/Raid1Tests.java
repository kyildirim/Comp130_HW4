package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import main.HW4;

public class Raid1Tests {

	@BeforeClass
	public static void initialize() {
		assertNotNull("[ERROR] Failed to initialize test image.", AutoGrader.testImage);
	}
	
	@Test
	public void testDisks(){
		HW4 MainClass = new HW4();
		MainClass.createRaid1Discs(AutoGrader.testImage);
		assertArrayEquals(MainClass.disc1, MainClass.disc2);
	}

}
