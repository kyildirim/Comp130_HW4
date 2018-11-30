package test;

import org.junit.ClassRule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import acm.graphics.GImage;

@RunWith(Suite.class)
@SuiteClasses({MenuTests.class, Raid0Tests.class, Raid1Tests.class, Raid5Tests.class, Raid10Tests.class})
public class AutoGrader {
	
	@ClassRule
    public static ErrorCollector errorCollector = new ErrorCollector();
	
	@ClassRule
	public static ExternalResource testImageRule = new ExternalResource(){
		@Override
		protected void before() throws Throwable{
			System.out.println("[INFO] Setting test image as "+TEST_IMAGE);
			testImage = new GImage(TEST_IMAGE);
		}
	};
	
	public static GImage testImage;
	
	private static final String TEST_IMAGE = "HW.png";
}