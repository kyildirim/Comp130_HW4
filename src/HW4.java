/*
 * File: HW4.java
 * -----------------------
 * This program saving of image data using RAID0, 1, 10 and 5 using
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


public class HW4 extends GraphicsProgram {

	public void run() {
		
		GImage original = new GImage(IMAGE_NAME);
		setSize ((int)original.getWidth()*2+1, (int) original.getHeight());
		GImage raidImage = new GImage(IMAGE_NAME);
		
		// initialize arrays
		initArray (original);
		
		//* RAID 1 - mirroring *//
		//create new discs using raid 1
		//raid1(original);
		
		// print contents of the discs
//		printDisc(disc1);
		//printDisc (disc2);
		
		//fail a disc randomly
		//failRaid(1);
		
		// print contents of the disc after fail
		//printDisc(disc1);
		//printDisc (disc2);
		
		// create a new image from raid 1 and save
		//raidImage = createRaidImage(1);
		
		//* RAID 0 - stripping *//
		//* Raid 0 create, fail and recovery *//
		//create new discs using raid 0
		raid0(original);
		// fail raid 0
		failRaid(0);
		// print contents of the disc after fail
		//printDisc(disc1);
		//printDisc (disc2);
		//create image after raid 0
		raidImage = createRaidImage(0);
				
		//display two images
		double xc = getWidth() / 2;
		double yc = getHeight() / 2;
		double x0 = xc - (original.getWidth() + raidImage.getWidth() + IMAGE_SEP) / 2;
		double x1 = x0 + original.getWidth() + IMAGE_SEP;
		add(original, 0, yc - original.getHeight() / 2);
		add(raidImage, x1, yc - raidImage.getHeight() / 2);
		//double xArrow = x1 - (ARROW_LENGTH + IMAGE_SEP) / 2;
		//addArrow(xArrow, yc, xArrow + ARROW_LENGTH, yc);
	}
	
	/*
	 * Create Raid images 
	 * @param 
	 * raid : specify which raid volume to use (e.g. 0, 1, 5, 10)
	 */
	private GImage createRaidImage(int raid) {
		double xc = getWidth() / 2;
		double yc = getHeight() / 2;
		GImage newImage = new GImage (IMAGE_NAME);
		switch (raid) {
		case 1: 
			//int [][] array1 = createImageRaid1fromTwoDiscs(disc1, disc2);
			int [][] array1 = createImagefromTwoDiscs(disc1, disc2);
			newImage = new GImage(array1);
			add(newImage, xc, yc - newImage.getHeight() / 2);		
			break;
		case 5: break;
		case 10: break;
		case 0: 
			int [][] array0 = createImageRaid0fromTwoDiscs(disc1, disc2);
			newImage = new GImage(array0);		
			add(newImage, xc, yc - newImage.getHeight() / 2);		
			break;		
		default:
		}
		return newImage;
		
	}
	/*
	 * Strip the image data into two discs for raid 1
	 */
	private int[][] createImageRaid1fromTwoDiscs (int[] disc1, int[] disc2) {
		int[][] array = new int [imageHeight][imageWidth];
		//println("new array size " + imageHeight + " height " + imageWidth + "height");
		
		int arrayRowIndex = 0;
		int arrayColIndex = 0;
		for (int disc1Index=0; disc1Index<disc1.length; disc1Index++) {
			array [arrayRowIndex][arrayColIndex] = disc1[disc1Index];
			arrayColIndex += 2;
			if (arrayColIndex == array[0].length && disc1Index!=0) {
				arrayRowIndex++;
				arrayColIndex = 0;
			}
		}
		printArray(array);

		return array;		
	}
	/*
	 * Mirror the image data into two discs for raid 0
	 */
	private int[][] createImageRaid0fromTwoDiscs (int[] disc1, int[] disc2) {
		int[][] array = new int [imageHeight][imageWidth];
		//println("new array size " + imageHeight + " height " + imageWidth + "height");
		
		int arrayRowIndex = 0;
		int arrayColIndex = 0;
		
		for (int disc1Index=0; disc1Index<disc1.length; disc1Index++) {
			array [arrayRowIndex][arrayColIndex] = disc1[disc1Index];
			arrayColIndex += 2;
			if (arrayColIndex == array[0].length && disc1Index!=0) {
				arrayRowIndex++;
				arrayColIndex = 0;
			}
		}
		//printArray(array);

		return array;
	}

	/*
	 * Create a new image from  raid 1 discs
	 */
	private int[][] createImagefromTwoDiscs (int[] disc1, int[] disc2) {
		int[][] array = new int [imageHeight][imageWidth];
		println("new array size " + imageHeight + " height " + imageWidth + "height");
		
		int arrayRowIndex = 0;
		int arrayColIndex = 0;
		for (int disc1Index=0; disc1Index<disc1.length; disc1Index++) {
			array [arrayRowIndex][arrayColIndex] = disc1[disc1Index];
			arrayColIndex += 2;
			if (arrayColIndex == array[0].length && disc1Index!=0) {
				arrayRowIndex++;
				arrayColIndex = 0;
			}
		}
		//printArray(array);

		return array;
	}
	/*
	 * Create a new image from  raid 0 discs
	 */
	private GImage createRaidImageOld(int raid) {
		GImage newImage = new GImage(IMAGE_NAME);
		switch (raid) {
		case 1: 
			int [][] array = createImagefromTwoDiscs(disc1, disc2);
			newImage = new GImage(array);
			double xc = getWidth() / 2;
			double yc = getHeight() / 2;
			add(newImage, xc, yc - newImage.getHeight() / 2);
			
			break;
		case 5: break;
		case 10:
		case 0:
		default:
		}
		return newImage;
	}
	/*
	 * Fail dics1 used for Raid 0 and Raid 1
	 */
	private void failDisc1() {
		println("failing disc1");
		for (int i = 0; i< disc1.length; i++) {
			int p1 = rgen.nextInt(disc1.length);
			int p2 = rgen.nextInt(disc1.length);
			int temp = disc1[p1];
			disc1[p1] = disc1[p2];
			disc1[p1] = temp;
		}
	}
	/*
	 * Fail dics2 used for Raid 0 and Raid 1
	 */
	private void failDisc2() {
		println("failing disc2");
		for (int i = 0; i< disc2.length; i++) {
			int p1 = rgen.nextInt(disc2.length);
			int p2 = rgen.nextInt(disc2.length);
			//println("changing pixels at " + p1 + " and " + p2 + " are " + disc2[p1] + " and " + disc2[p2]);
		
			int temp = disc2[p1];
			disc2[p1] = disc2[p2];
			disc2[p2] = temp;
		}
	}
	/*
	 * Fail discs used for Raid Volumes
	 * @param 
	 * raid : specify the type of the raid
	 */
	private void failRaid(int raid) {
		int disc = 0; 
		switch (raid) {
		case 1: 
			disc = rgen.nextInt(1);
			if (disc == 1) {
				// fail disc 1
				failDisc1();
			}else {
				// fail disc 2
				failDisc2();
			}
			break;
		case 5: break;
		case 10:
		case 0:
		default:
		}
	}
	
	
	
	/*
	 * Create Raid 1 volumes on two discs
	 */
	private void raid0(GImage image) {
		int[][] array = image.getPixelArray();
		
		//println("image size " + array.length + " height " + array[0].length + "height");
		//printArray(array);
		int numberPixels = array.length * array[0].length;
		
		//initialize discs
		disc1 = new int[numberPixels/2];
		disc2 = new int[numberPixels/2];
		
		// printcontents of disc
		
		// Your code starts here
		//printArray(array);
		int height = array.length;
		int disc1Index = 0;
		int disc2Index =0;
		for (int row =0; row<array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				int currentPixelValue = array[row][col];
				//print("&"+ currentPixelValue);
				if ((disc1Index + disc2Index) % 2 == 0) {
					disc1[disc1Index] = currentPixelValue;
					disc1Index++;
				} else {
					disc2[disc2Index] = currentPixelValue;
					disc2Index++;
				}
			}
			
		}	
		// Your code ends here	
	}
	/*
	 * Display the contents of a disc
	 * @param 
	 * int [] disc - the array showing the contents of a disc
	 */
	private void printDisc(int[] disc){
		println("printing dics");
		for (int i =0; i< disc1.length; i++) {
			print(disc[i]+ " ");
		}
		println();
	}
	/*
	 * Display the contents of the image data on 2D array
	 * @param 
	 * int [][] disc - image data
	 */
	private void printArray(int[][] array) {
		
		
		for (int i =0; i<array.length; i++) {
			for (int k=0; k<array[i].length; k++) {
				int pixel = array[i][k];
				print(pixel);
				//print("[" + GImage.getAlpha(pixel)+ "/"+ GImage.getRed(pixel) + "/" +GImage.getGreen(pixel) + "/" + GImage.getBlue(pixel) + "]");
			}
			println();
		}
		
	}
	
	/*
	 * Initialize the size of the new image
	 */
	private void initArray(GImage image) {
		GDimension dim = image.getSize();
		imageWidth = (int) dim.getWidth();
		imageHeight = (int) dim.getHeight() ;
		println(dim.getHeight() + " height " + dim.getWidth() + " width");
	}
	/*
	 * Adds an arrow to the canvas connecting the points (x0, y0)
	 * and (x1, y1).
	 */
	private void addArrow(double x0, double y0, double x1, double y1) {
		add(new GLine(x0, y0, x1, y1));
		double theta = GMath.angle(x0, y0, x1, y1);
		addPolarLine(x1, y1, ARROWHEAD_SIZE, theta + 135);
		addPolarLine(x1, y1, ARROWHEAD_SIZE, theta - 135);
	}

	/*
	 * Adds a line segment that begins at the point (x, y) and then
	 * extends r pixels in direction theta.
	 */
	private void addPolarLine(double x, double y, double r, double theta) {
		double dx = r * GMath.cosDegrees(theta);
		double dy = -r * GMath.sinDegrees(theta);
		add(new GLine(x, y, x + dx, y + dy));
	}

	/* Private constants */
	private static final double IMAGE_SEP = 50;
	private static final double ARROWHEAD_SIZE = 8;
	private static final String IMAGE_NAME = "HW.png";
	private static final double ARROW_LENGTH = IMAGE_SEP / 2;
	private int[] disc1;
	private int[] disc2;
	private int[] disc3;
	private int[] disc4;
	int imageWidth = 0;
	int imageHeight = 0;

	/* Instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
