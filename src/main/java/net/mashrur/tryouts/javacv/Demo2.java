import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.CanvasFrame;
import static com.googlecode.javacpp.Loader.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * <p>
 * A Demo of JavaCV. Code is from {@link http://stackoverflow.com/a/11522705}
 * </p>
 * 
 */
public class Demo2 {
    public static void main(String[] args) {
	/*
	JFileChooser f=new JFileChooser();
	int result=f.showOpenDialog(f);//show dialog box to choose files
	File myfile=null;
	String path="";
	if(result==0){
	    myfile=f.getSelectedFile();//selected file taken to myfile
	    path=myfile.getAbsolutePath();//get the path of the file
	    }*/
	String path = args[0];
	IplImage src = cvLoadImage(path);//hear path is actual path to image
	IplImage grayImage    = IplImage.create(src.width(), src.height(), IPL_DEPTH_8U, 1);
	cvCvtColor(src, grayImage, CV_RGB2GRAY);
	cvThreshold(grayImage, grayImage, 127, 255, CV_THRESH_BINARY);
	CvSeq cvSeq=new CvSeq();
	CvMemStorage memory=CvMemStorage.create();
	cvFindContours(grayImage, memory, cvSeq, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
	
	while (cvSeq != null && !cvSeq.isNull()) {
	    CvRect rect=cvBoundingRect(cvSeq, 0);
	    int x=rect.x();
	    int y=rect.y();
	    int h=rect.height();
	    int w=rect.width();

	    System.out.println("Rectangles " + x + ", " + y + ", " + w + ", " + h);
	    if (10 < w/h || w/h < 0.1){
		cvRectangle(src, cvPoint(x, y), cvPoint(x+w, y+h), CvScalar.RED, 1, CV_AA, 0);
	    }
	    cvSeq=cvSeq.h_next();
	}
	CanvasFrame cnvs=new CanvasFrame("Beam");
	cnvs.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	cnvs.showImage(src);
	//cvShowImage("Final ", src);
    }
}
