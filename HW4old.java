/*
 * File: FlipVertical.java
 * -----------------------
 * This program tests the flipVertical method from Chapter 11.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

/**
 * This class tests the flipVertical method.
 */
public class HW4 extends GraphicsProgram {

	public void run() {
		GImage original = new GImage("Candle.gif");
		
		// print details of the image
		//printDetails (original);
		
		//* Raid 0 create, fail and recovery *//
		//create new discs using raid 0
		//raid0(original);
		// fail raid 0
		//failRaid(0);
		// print contents of the disc
		printDisc(disc1);
		printDisc (disc2);
		//create image after raid 0
		createRaidImage(0);	
		
		//* Raid 1 create, fail and recovery *//
		//create new discs using raid 1
		raid1(original);		
		// print contents of the disc
//		//printDisc(disc1);
		//printDisc (disc2);
		//fail a disc randomly
		failRaid(1);	
		// print contents of the disc
		//printDisc(disc1);
		//printDisc (disc2);
		//recover image 
		recoverRaid1();
		// create image after recover and save
		createRaidImage(1);
//		double xc = getWidth() / 2;
//		double yc = getHeight() / 2;
//		double x0 = xc - (original.getWidth() + raidImage.getWidth() + IMAGE_SEP) / 2;
//		double x1 = x0 + original.getWidth() + IMAGE_SEP;
//		add(original, x0, yc - original.getHeight() / 2);
//		add(raidImage, x1, yc - raidImage.getHeight() / 2);
//		double xArrow = x1 - (ARROW_LENGTH + IMAGE_SEP) / 2;
//		addArrow(xArrow, yc, xArrow + ARROW_LENGTH, yc);
	}
	private int[][] createImageRaid1fromTwoDiscs (int[] disc1, int[] disc2) {
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
		printArray(array);
//		arrayRowIndex = 0;
//		arrayColIndex = 1;
//		for (int disc2Index=0; disc2Index<disc2.length; disc2Index++) {
//			array [arrayRowIndex][arrayColIndex] = disc1[disc2Index];
//			arrayColIndex += 2;
//			if (disc2Index % array[0].length == 0) {
//				arrayRowIndex++;
//				arrayColIndex = 0;
//			}
//		}
		return array;
	}
	private int[][] createImageRaid0fromTwoDiscs (int[] disc1, int[] disc2) {
		
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
		printArray(array);
//		arrayRowIndex = 0;
//		arrayColIndex = 1;
//		for (int disc2Index=0; disc2Index<disc2.length; disc2Index++) {
//			array [arrayRowIndex][arrayColIndex] = disc1[disc2Index];
//			arrayColIndex += 2;
//			if (disc2Index % array[0].length == 0) {
//				arrayRowIndex++;
//				arrayColIndex = 0;
//			}
//		}
		return array;
	}
	private void createRaidImage(int raid) {
		double xc = getWidth() / 2;
		double yc = getHeight() / 2;
		switch (raid) {
		case 1: 
			int [][] array1 = createImageRaid1fromTwoDiscs(disc1, disc2);
			GImage newImage1 = new GImage(array1);
		
			add(newImage1, xc, yc - newImage1.getHeight() / 2);
			
			break;
		case 5: break;
		case 10: break;
		case 0: 
			int [][] array0 = createImageRaid0fromTwoDiscs(disc1, disc2);
			GImage newImage0 = new GImage(array0);
			
			add(newImage0, xc, yc - newImage0.getHeight() / 2);
			
			break;
		
		default:
		}
		
	}
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
			disc = rgen.nextInt(1);
			if (disc == 1) {
				// fail disc 1
				failDisc1();
			}else {
				// fail disc 2
				failDisc2();
			}
			break;
		default:
		}
	}
	private void printDetails(GImage image) {
		GDimension dim = image.getSize();
		imageWidth = (int) dim.getWidth();
		imageHeight = (int) dim.getHeight() ;
		println(dim.getHeight() + " height " + dim.getWidth() + " width");
	}
	private void printDisc(int[] disc){
		println("printing dics");
		for (int i =0; i< disc1.length; i++) {
			print(disc[i]+ " ");
		}
		println();
	}
	
	/*
	 * Creates a new image which consists of the bits in the original
	 * flipped vertically around the center line.
	 */
	private void raid1(GImage image) {
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
				
//				bytes = new Integer(currentPixelValue).toString().getBytes();
//				printBytes();
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

	private void raid0(GImage image) {
		int[][] array = image.getPixelArray();
		
		//println("image size " + array.length + " height " + array[0].length + "height");
		
		int numberPixels = array.length * array[0].length;
		
		//initialize discs
		disc1 = new int[numberPixels];
		disc2 = new int[numberPixels];
		
		//printArray(array);
		int height = array.length;
		int disc1Index = 0;
		int disc2Index =0;
		for (int row =0; row<array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				int currentPixelValue = array[row][col];
		
				//print("&"+ currentPixelValue);
				disc1[disc1Index] = currentPixelValue;
				disc1Index++;
				
				disc2[disc2Index] = currentPixelValue;
				disc2Index++;
				
			}
			
		}
		
		
		// Your code ends here
	
	}
	public void printBytes() {
		//println("printing bytes");
		print("{");
		for (int i = 0; i < bytes.length; i++) {
			
			print(bytes[i] + "<");
		}
		print("}");
	}
	public static long getUInt32(byte[] bytes) {
	    long value = byteAsULong(bytes[0]) | (byteAsULong(bytes[1]) << 8) | (byteAsULong(bytes[2]) << 16) | (byteAsULong(bytes[3]) << 24);
	    return value;
	}
	public static long byteAsULong(byte b) {
	    return ((long)b) & 0x00000000000000FFL; 
	}
	
	private void recoverRaid1() {
		
	}
	
	private void printArray(int[][] array) {
		for (int i =0; i<array.length; i++) {
			for (int k=0; k<array[i].length; k++) {
				int pixel = array[i][k];
//				byte[] mybyte = new byte [] {
//					(byte)(pixel >>> 24), 
//					(byte)(pixel >>> 16), 
//					(byte)(pixel >>> 8), 
//					(byte)(pixel) 
//				};
//				print(pixel + "-");
//				for (int m =0; m < mybyte.length; m++) {
//					print(mybyte[m] +"/");
//				}
				print(pixel);
				//print("[" + GImage.getAlpha(pixel)+ "/"+ GImage.getRed(pixel) + "/" +GImage.getGreen(pixel) + "/" + GImage.getBlue(pixel) + "]");
			}
			println();
		}
		
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
	private static final double ARROW_LENGTH = IMAGE_SEP / 2;
	private int[] disc1;
	private int[] disc2;
	private int[] disc3;
	private int[] disc4;
	byte[] bytes;
	int imageWidth = 0;
	int imageHeight = 0;

	/* Instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
