package dev.licensex.server.utils.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AABanner {

	public static int w = 600;
	public static int h = 600;
	public static String myChar= "*";
	public static int clarity = 8;
	public static int percent = 20;
	public static String myFontName="Calibri";
	public static int myFontStyle = Font.PLAIN;
	public static boolean debug = false;
	static HashMap<Character, LetterBlock> map = new HashMap<Character, LetterBlock>();

	public static void print(String line) {

		for(int i=0;i<line.length();i++) {
			try {
			parseChar(line.charAt(i));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error at prasing char " + line.charAt(i));
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		for(int i=0;i<clarity;i++) {
			for(int j=0;j<line.length();j++) {
				printLineOfChar(line.charAt(j),i);
				System.out.print(" ");
			}
			System.out.println(" ");
		}

	}

	private static void printLineOfChar(char c, int i) {
		map.get(c).printLine(i);
		
	}

	private static void parseChar(char c) {
		if(map.containsKey(c)) return;
		
		LetterBlock block = new LetterBlock(clarity);
		
		
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		
		Font f; //= new Font("Lucida Handwriting", Font.BOLD,500);
		
		g.setBackground(Color.WHITE);
		g.setPaint(Color.BLACK);

		f = new Font(myFontName, myFontStyle ,500);
		g.setFont(f);
		g.setPaint(Color.WHITE);
		g.fillRect(0, 0, w, h);
		g.setPaint(Color.BLACK);
		String letter = c + "";
		
		FontMetrics fontMetrics = g.getFontMetrics(f);
	     int lw = fontMetrics.stringWidth(letter);
	     int lh = fontMetrics.getAscent();
	     //int lh = fontMetrics.getHeight();
	     
	     //int hScale = h / lh;
	     //int wScale = w / lw;
	     
	     //int pfs = (int) (10 * (hScale > wScale ? hScale : wScale) * 0.7f) ;
	     
	     //int pfs = (w / lw ) * 10;
	     
	     //System.out.println(pfs);
	     //f  = new Font("Calibri", Font.BOLD,pfs);
	     
	     g.setFont(f);
	     fontMetrics = g.getFontMetrics(f);
	     lw = fontMetrics.stringWidth(letter);
	     lh = fontMetrics.getAscent();
	     int fh = fontMetrics.getHeight();
	     
	     g.drawString(letter, (w-lw) / 2 , ((h-fh)/2)+lh);
	     
	     if(AABanner.debug) {	     
		     System.out.println("Drew at x : " + (w-lw) / 2 + "  , y  " + (((h-fh)/2)+lh));
		     System.out.println("String width is " + lw + " ,String Ascent is " + lh + " ,font height is " + fh);
		     
		     

			 try {
				ImageIO.write(img,"PNG",new File("test.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
		 // calculate inital Point p and final point q
		 
		int[] points = {Integer.MAX_VALUE, 0 , Integer.MAX_VALUE , 0};
		 
		// box
		
		
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				if(new Color(img.getRGB(i, j)).getRed() == 0) {
					
					if(points[0] > i) {
						points[0] = i;
					} 
					
					if(points[1] < i) {
						points[1] = i;
					}
					
					if(points[2] > j) {
						points[2] = j;
					}
					
					if(points[3] < j) {
						points[3] = j;
					}
				}
			}
		}
		
		
		if(AABanner.debug)  {
			for(int i=0;i<points.length;i++) {
				System.out.print( points[i] + " , ");
			}
			System.out.println("\n");
		}
		
		// adjust bounds..
		int deltaX = lw - (points[1] - points[0]);
		if( deltaX > 0 ) {
			
			if(points[0] - (deltaX /2) > 0)
				points[0] -= deltaX /2;
			else 
				points[0] = 0;
			
			if(points[1] + (deltaX /2) < w )
				points[1] += deltaX /2;
			else
				points[1] = w;
		}
		
		
		int deltaY = lh - (points[3]-points[2]);
		if(deltaY > 0) {
			
			if(points[2] - (deltaY /2) > 0)
				points[2] -= deltaY /2;
			else
				points[2] = 0;
			
			if(points[3] + (deltaY /2) < h) 
				points[3] += deltaY /2;
			else
				points[3] = h;
		}
		
		
		if(AABanner.debug)  {
			for(int i=0;i<points.length;i++) {
				System.out.print( points[i] + " , ");
			}
			System.out.println("\n");
		}
		
		 int first_point_x = points[0];
		 int first_point_y = points[2];
		 
		 int second_point_x = points[1];
		 int second_point_y = points[3];
		 
		 int new_image_width  = second_point_x - first_point_x;
		 int new_image_height = second_point_y - first_point_y; 
		 
		 int x_inc = new_image_width  / clarity;
		 int y_inc = new_image_height / clarity;
		 int th = (int)( x_inc * y_inc * (percent / 100.0f) );
		 
		 if(debug) {
			 System.out.println("X inc is " + x_inc + " Y inc is " + y_inc);
			 
		 }
			 int seq = 0;
			 String line = "";
			 for(int j=first_point_y;j<second_point_y;j+=y_inc) {
				 
				 for(int i=first_point_x;i<second_point_x;i+=x_inc) { 
				 int black_count = 0;
				 
				 for(int k=0;k< x_inc && i+k < w;k++) {
					 for(int l=0;l<y_inc && j+l < h;l++) {
						 
						 if(new Color(img.getRGB(i+k, j+l)).getRed() == 0) {
							 black_count++;
						 }
						 
					 }
				 }
				 if(black_count > th) {
					 line += myChar;
				 } else {
				 	line += " ";
				 }
			 }
			 
				 block.putLine(line, seq++);
				 line = "";
		 }

		map.put(c,block);
	}

}
