package main;
/*
 * Student Name: <Enter your name here>
 * File: HW4.java
 * -----------------------
 * This program saving of image data using RAID0, 1 and 10 using
 * multidimensional arrays. Discs on raid structure is simulated
 * using single dimensional array.
 * The program reads in an image data, converts into 2D int array.
 * It simulates the failure of the disc by randomly changing the
 * contents of the disc (array). It constructs a new image
 * out of the raid discs and displays the original and the new image 
 * on canvas.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;
import acm.util.*;
import java.io.*;
import java.util.*;


public class Comp130_HW4_F18 extends GraphicsProgram {

	public void run() {
		
		println("This program simulates the saving of an image on RAID Volumes");
		//Your code starts here
		
	
		//Your code ends here			
	}
	
	/*
	 * Create a new image using the Raid configuration 
	 * @param 
	 * raid : specify which raid volume to use (e.g. 0, 1, 10)
	 */
	public GImage createRaidImage(int raid) {
		
		GImage newImage = new GImage (imageNameInput);
		
		
		return newImage;		
	}
	
	
	/*
	 * Fail discs used for Raid Volumes
	 * @param 
	 * raid : specify the type of the raid
	 */
	public void failRaid(int raid) {
		//Your code starts here
		
		
		//Your code ends here
	}

	/*
	 * Create Raid 0 volume on two discs
	 */
	public void createRaid0Discs(GImage image) {
		//Your code starts here
		
		// Your code ends here	
	}
	/*
	 * Create Raid 1 volume on two discs
	 */
	public void createRaid1Discs(GImage image) {
		//Your code starts here
	
		// Your code ends here
	}
	/*
	 * Create Raid 10 volume on four discs
	 */
	public void createRaid10Discs(GImage image) {
		//Your code starts here
		
		// Your code ends here
	}

	/*
	 * write pixel values of an image into a file
	 */
	public void writeImageFile (int[][] pixels, String raidType) {
		
		//Your code starts here
		
		// Your code ends here
		
	}
	//* Other Helper Methods  *//
	
	//Your code starts here
	
	// Your code ends here
	
	//* DO NOT MODIFY THE BELOW CODE  *//
	/* Private constants */
	private static final double IMAGE_SEP = 30;

	/* Instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private  String imageNameInput = "";
	private int[] disc1;
	private int[] disc2;
	private int[] disc3;
	private int[] disc4;
	int failDisc = 1;
	int imageWidth = 0;
	int imageHeight = 0;
}

