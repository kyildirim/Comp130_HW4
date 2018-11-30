/*
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
package main;
import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;
// IO lib
import acm.util.*;
import java.io.*;
import java.util.*;


public class HW4 extends GraphicsProgram {

	public void run() {
		
		println("This program simulates the saving of an image on Raid Volumes");
		//Your code starts here
		//imageNameInput  = readLine ("Specify the name of the image file:");
		//println("Please choose the Raid configuration you want to use");
		imageNameInput = "Candle.gif";
		//imageNameInput = "HW.png";
		GImage original = new GImage(imageNameInput);
		GImage raidImage = new GImage(imageNameInput);

		// initialize arrays
		initArray (original);
		
		int userSelection = -1;
		while (userSelection == -1) {
			//userSelection = readInt("Select 1, 0, or 10: ");
			userSelection = 1;

			switch (userSelection){
			case 0:		
				// Raid 0 create, fail and recovery //
				//create new discs using raid 0
				createRaid0Discs(original);
				// fail raid 0
				failRaid(0);
				//printDisc(disc1);
				//printDisc(disc2);
				//create image after raid 0
				raidImage = createRaidImage(0);
				break;
			case 1: 
				// Raid 1 create, fail and recovery 
				//println("raid 1");
				//create new discs using raid 0
				createRaid1Discs(original);
				
				// fail raid 0
				failRaid(1);
				printDisc(disc1);
				printDisc(disc2);
				//create image after raid 0
				raidImage = createRaidImage(1);
				break;
			case 5: 
				// Raid 5 create, fail and recovery //
				//println("raid 5");			
				//create new discs using raid 5
				//raid5(original);
				// fail raid 0
				//failRaid(5);
				// print contents of the disc after fail
				//printDisc(disc1);
				//printDisc (disc2);
				//printDisc (disc3);
				//create image after raid 5
				//raidImage = createRaidImage(5);
				//raidImage = recoverRaid5Image();
				break;
			case 10: 
				// RAID 0 - stripping //
				//println("raid 10");
				//create new discs using raid 0
				//createRaid10Discs(original);
				// fail raid 0
				//failRaid(10);
				// print contents of the disc after fail
				//printDisc(disc1);
				//printDisc (disc2);
				//create image after raid 0
				//raidImage = createRaidImage(0);
				break;
			default:
				println("invalid selection"); 
				userSelection = -1;
				break;
			}
			
		}

		//display two images
		double x0 = IMAGE_SEP;
		double x1 = x0 + original.getWidth() + IMAGE_SEP;
		add(original, x0, IMAGE_SEP);
		add(raidImage, x1,IMAGE_SEP);		
		setSize((int)(original.getWidth()+raidImage.getWidth()+3*IMAGE_SEP), (int) (2*IMAGE_SEP+ original.getHeight()+ raidImage.getHeight()));
		//Your code ends here			
	}
	
	/*
	 * Create a new image using the Raid configuration 
	 * @param 
	 * raid : specify which raid volume to use (e.g. 0, 1, 10)
	 */
	private GImage createRaidImage(int raid) {
		
		GImage newImage = new GImage (imageNameInput);

		double xc = getWidth() / 2;
		double yc = getHeight() / 2;
		
		switch (raid) {
		case 0: 
			//int [][] array0 = createImageRaid0fromOneDisc();
			int [][] array0 = createImageRaid0fromTwoDiscs();
			writeImageFile(array0, "0");			
			newImage = new GImage(array0);			
			break;		
		case 1: 
			int [][] array1 = createImageRaid1fromOneDisc();
			newImage = new GImage(array1);
			writeImageFile(array1, "1");	
			break;
		case 10: 
			//writeImageFile(array10, "10");
			break;
		case 5: 
			/*
			int [][] array5 = new int [imageHeight][imageWidth];
			switch (Raid5FailDisc) {
			case 1:
				array5 = createImageRaid5fromTwoDiscs(disc2, disc3);
				
				break;
			case 2:
				array5 = createImageRaid5fromTwoDiscs(disc1, disc3);
					
				break;
			case 3:	
				array5 = createImageRaid5fromTwoDiscs(disc1, disc2);
					
				break;
			
			*/

		}
		return newImage;		
	}
	
	
	/*
	 * Fail discs used for Raid Volumes
	 * @param 
	 * raid : specify the type of the raid
	 */
	private void failRaid(int raid) {
		//Your code starts here
		int disc = 0; 
		switch (raid) {
		case 1: case 0:
			disc = rgen.nextInt(1,2);
			println("failing " + disc);
			failDisc = disc;
			if (disc == 1) {
				// fail disc 1
				failDisc1();
			}else {
				// fail disc 2
				failDisc2();
			}
			break;
		case 5: 
			/*
			disc = rgen.nextInt(1,3);
			if (disc == 1) {
				// fail disc 1
				failDisc1();
			}else if (disc == 2){
				// fail disc 2
				failDisc2();
			} else {
				// fail disc 3
				failDisc3();
			}
			Raid5FailDisc = disc;	
			*/
			break;
		case 10:
			break;
		}
		//printDisc(disc1);
		//printDisc(disc2);
		
		//Your code ends here
	}

	/*
	 * Create Raid 0 volume on two discs
	 */
	private void createRaid0Discs(GImage image) {
		//Your code starts here
		int[][] array = image.getPixelArray();

		int numberPixels = array.length * array[0].length;
		
		//initialize discs
		disc1 = new int[numberPixels/2];
		disc2 = new int[numberPixels/2];
		
		int disc1Index = 0;
		int disc2Index =0;
		for (int row =0; row<array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				int currentPixelValue = array[row][col];

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
	 * Create Raid 1 volume on two discs
	 */
	private void createRaid1Discs(GImage image) {
		//Your code starts here
		int[][] array = image.getPixelArray();
		int numberPixels = array.length * array[0].length;
		
		//initialize discs
		disc1 = new int[numberPixels];
		disc2 = new int[numberPixels];

		int disc1Index = 0;
		int disc2Index =0;
		for (int row =0; row<array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				int currentPixelValue = array[row][col];
				//print("&"+ currentPixelValue);
				// copy same pixel into two different array
				disc1[disc1Index] = currentPixelValue;
				disc1Index++;
				
				disc2[disc2Index] = currentPixelValue;
				disc2Index++;
			}		
		}	
		
		
		// Your code ends here
	}
	/*
	 * Create Raid 10 volume on four discs
	 */
	private void createrRaid10Discs(GImage image) {
		//Your code starts here
		// Your code ends here
	}

	/*
	 * Initialize the size of the new image
	 */
	private void initArray(GImage image) {
		imageWidth = (int) image.getWidth();
		imageHeight = (int) image.getHeight() ;
	}
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
	
	//* Other Helper Methods  *//
	/*
	 * Fail dics1 used for Raid 0 and Raid 1
	 */
	private void failDisc1() {
		println("failing disc1");
		for (int i = 0; i< 100; i++) {
			//if (rgen.nextBoolean()) {
			if (i%5== 0) {
				disc1[i] = GImage.createRGBPixel(0, 0, 0, 255);
			}
		}
		
		//swap contents temporarly
//		for (int i = 0; i< disc1.length; i++) {
//			int p1 = rgen.nextInt(disc1.length);
//			int p2 = rgen.nextInt(disc1.length);
//			int temp = disc1[p1];
//			disc1[p1] = disc1[p2];
//			disc1[p1] = temp;
//		}
	}
	/*
	 * Fail dics2 used for Raid 0 and Raid 1
	 */
	private void failDisc2() {
		println("failing disc2");
		//replace by black
		for (int i = 0; i< disc2.length; i++) {
			//if (rgen.nextBoolean()) { // random fail
			if (i%5== 0) { // controlled fail
				disc2[i] = GImage.createRGBPixel(0, 0, 0, 255);
			}
		}
		// swaping pixels
		/*
		 for (int i = 0; i< disc2.length; i++) {
		 
			int p1 = rgen.nextInt(disc2.length);
			int p2 = rgen.nextInt(disc2.length);
			//println("changing pixels at " + p1 + " and " + p2 + " are " + disc2[p1] + " and " + disc2[p2]);
		
			int temp = disc2[p1];
			disc2[p1] = disc2[p2];
			disc2[p2] = temp;
		}
		*/
	}
	/*
	 * Create pixesls from striped image data from one disc for raid 0
	 */
	private int[][] createImageRaid0fromOneDisc () {
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
	 * Create pixesls from striped image data from two discs for raid 0
	 */
	private int[][] createImageRaid0fromTwoDiscs () {
		
		int[][] array = new int [imageHeight][imageWidth];
		//printArrayDetails(array);	
		int arrayRowIndex = 0;
		int arrayColIndex = 0;
		
		for (int disc1Index=0; disc1Index<disc1.length; disc1Index++) {
			int pixel = disc1[disc1Index];
			//println("disc1 pixel " + pixel);
			array [arrayRowIndex][arrayColIndex] = pixel;
			arrayColIndex += 2;
			if (arrayColIndex >= array[0].length && disc1Index!=0) {
				arrayRowIndex++;
				arrayColIndex = 0;
			}
		}
		arrayRowIndex = 0;
		arrayColIndex = 1;
		for (int disc2Index=0; disc2Index<disc2.length; disc2Index++) {
			int pixel = disc2[disc2Index];
			
			array [arrayRowIndex][arrayColIndex] = pixel;
			//println("disc2 pixel " + pixel + "@ " + arrayRowIndex +"," + arrayColIndex);
			arrayColIndex += 2;
			if (arrayColIndex >= array[0].length && disc2Index!=0) {
				arrayRowIndex++;
				arrayColIndex = 1;
			}
		}
		writeImageFile (array, "Raid0");

		return array;
	}
	/*
	 * Create pixels from mirrored data on two discs for raid 1
	 */
	private int[][] createImageRaid1fromOneDisc () {
		int[][] array = new int [imageHeight][imageWidth];
		//printDisc(disc1);
		//printDisc(disc2);
		int arrayRowIndex = 0;
		int arrayColIndex = 0;
		if (failDisc == 1 ) {
			for (int disc1Index=0; disc1Index<disc1.length; disc1Index++) {
				array [arrayRowIndex][arrayColIndex] = disc1[disc1Index];
				arrayColIndex++;
				
				if (arrayColIndex == array[0].length && disc1Index!=0) {
					arrayRowIndex++;
					arrayColIndex = 0;
				}
			}
		} else if (failDisc == 2){
			for (int disc2Index=0; disc2Index<disc2.length; disc2Index++) {
				array [arrayRowIndex][arrayColIndex] = disc2[disc2Index];
				arrayColIndex++;
				
				if (arrayColIndex == array[0].length && disc2Index!=0) {
					arrayRowIndex++;
					arrayColIndex = 0;
				}
			}
		}
		println("in creating 1 image");
		printDisc(disc1);
		printDisc(disc2);
		printArray(array);

		return array;		
	}
	/*
	 * write pixel values of an image into a file
	 */
	private void writeImageFile (int[][] pixels, String raidType) {
		//printArrayDetails(pixels);
		String fileName = "raidImage" + raidType + ".txt";
		try {
			PrintWriter wr = new PrintWriter( new FileWriter(fileName), true);
			for (int i=0; i< pixels.length; i++) {
				for (int j=0; j< pixels[j].length; j++) {
					wr.print(pixels[i][j]+" ");
				}
				wr.println();
			}
			wr.close();
			
		}catch (IOException ex) {
			throw new ErrorException (ex);
		}
	}
	/*
	 * read pixel values of an image from a file
	 */
	private int[][] readImageFile (String raidType) {
		int[][] pixels = new int[imageHeight][imageWidth];
		String fileName = "raidImage" + raidType + ".txt";
		try {
			BufferedReader rd = new BufferedReader( new FileReader(fileName));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				//println(line);
//				for (int i=0; i< pixels.length; i++) {
//					for (int j=0; j< pixels[i].length; j++) {
//						pixels[i][j] = (int) Integer.parseInt(readPixel(line, j+1));
//					}
//					
//				}

			}
			rd.close();
			
		}catch (IOException ex) {
			throw new ErrorException (ex);
		}
		return pixels;
	}
	/*
	 * read pixel value at a specific location in a string
	 */
	private String readPixel(String line, int location) {
		String result = "";
		int spStLoc = line.indexOf(" ", location);
		result = line.substring(location-1, spStLoc);
		return result;
		
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
	private void printArrayDetails(int [][] array) {
		println("array height=" + array.length + " widht=" + array[0].length);
	}
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
