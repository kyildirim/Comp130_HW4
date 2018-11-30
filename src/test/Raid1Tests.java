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
		assertNotNull("[ERROR] Failed to initialize ErrorCollector", AutoGrader.errorCollector);
	}
	
	@SuppressWarnings("static-method")
	@Test
	public void testDisks(){
		HW4 MainClass = new HW4();
		MainClass.createRaid1Discs(AutoGrader.testImage);
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
			assertArrayEquals(MainClass.disc1, MainClass.disc2); //Check if disc contents are the same
		}catch (Throwable t){
			AutoGrader.errorCollector.addError(t);
		}
		
	}

}
