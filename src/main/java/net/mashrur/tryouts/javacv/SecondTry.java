package net.mashrur.tryouts.javacv;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_core.IplImage;


import com.googlecode.javacv.CanvasFrame;
import javax.swing.JFrame;

public class SecondTry{

    public static void main(String [] args){

	IplImage iplImage  = IplImage.createFrom(getImage());
	CanvasFrame frame = new CanvasFrame("SecondTry");
	
	frame.showImage(iplImage);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
    }

    public static BufferedImage getImage(){
	
	int width = 600;
	int height = 600;
	int gutter = 10;
	
	BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_BYTE_GRAY);

	
	Graphics2D g = (Graphics2D) bi.getGraphics();
	
	g.setColor(Color.white);
	g.fillRect(0,0,width,height);
	
	g.setColor(Color.red);
	g.fillRect(gutter,gutter,width-2*gutter,height-2*gutter);

	bi.flush();
	
	return bi;
    }

}