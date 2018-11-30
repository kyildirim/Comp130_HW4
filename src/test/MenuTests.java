package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import main.HW4;

public class MenuTests {
	
	//XXX For slow core speeds increase the timeout value
	@Rule
	public Timeout globalTimeout = new Timeout(10, TimeUnit.SECONDS);

	@SuppressWarnings("static-method")
	@Test
	public void menuNavigationTest() {
		//Run menu item 0 (RAID 0)
		InputStream menuInput = new ByteArrayInputStream("0".getBytes());
		System.setIn(menuInput);
		HW4 MainClass = new HW4();
		MainClass.run();
		//Run menu item 1 (RAID 1)
		menuInput = new ByteArrayInputStream("1".getBytes());
		System.setIn(menuInput);
		MainClass = new HW4();
		MainClass.run();
		//Run menu item 10 (RAID 10)
		menuInput = new ByteArrayInputStream("10".getBytes());
		System.setIn(menuInput);
		MainClass = new HW4();
		MainClass.run();
		//Run illegal menu item (2)
		menuInput = new ByteArrayInputStream("2".getBytes());
		System.setIn(menuInput);
		ByteArrayOutputStream outRaw = new ByteArrayOutputStream();
		PrintStream menuOutput = new PrintStream(outRaw, true);
		System.setOut(menuOutput);
		Thread hangingThread = new Thread(MainClass);
		hangingThread.start();
		try {
			//XXX For slow core speeds increase the sleep time
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotNull(hangingThread.getStackTrace()); //See if the program terminates or still awaiting user input
		hangingThread.interrupt(); //Interrupt the awaiting thread
		assertTrue(outRaw.toString().toLowerCase().contains("invalid")); //XXX Assert error message (assumes the existence of invalid keyword)
	}

}
