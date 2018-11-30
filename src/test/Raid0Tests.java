package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import main.HW4;

public class Raid0Tests {

	@BeforeClass
	public static void initialize() {
		assertNotNull("[ERROR] Failed to initialize test image.", AutoGrader.testImage);
		assertNotNull("[ERROR] Failed to initialize ErrorCollector", AutoGrader.errorCollector);
	}
	
	@Test
	public void testDisks(){
		HW4 MainClass = new HW4();
		MainClass.createRaid0Discs(AutoGrader.testImage);
		try{
			assertNotNull(MainClass.disc1);
		}catch (Throwable t){
			AutoGrader.errorCollector.addError(t); //Null-check disc
		}
		try{
			assertNotNull(MainClass.disc2); //Null-check disc
		}catch (Throwable t){
			AutoGrader.errorCollector.addError(t);
		}
		try{
			assertTrue(MainClass.disc1.equals(MainClass.disc2)); //Check if disc contents are different
		}catch (Throwable t){
			AutoGrader.errorCollector.addError(t);
		}
	}

}
