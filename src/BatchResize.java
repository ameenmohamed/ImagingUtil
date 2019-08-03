import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.thebuzzmedia.imgscalr.Scalr;




public class BatchResize {

	public static void main(String[] args) throws IOException {
		

		String srcdir = "/Users/mohamedameen/Downloads/gausiaacademy/assets/images";
		String destdir = "/Users/mohamedameen/amroot/workout/ga";
		File src = new File(srcdir);
		
		if(src.isDirectory()){
			String[] filelist = src.list();
			File flt = new File(destdir);
			if(!flt.exists()){
					flt.mkdir();
				}
			long startTime = System.nanoTime();	
			FileReSize fs = new FileReSize(srcdir,destdir);
			
			long endTime = System.nanoTime();
			long totTime = endTime - startTime;
			System.out.println("Time to resize "+TimeUnit.NANOSECONDS.toSeconds(totTime) +" seconds" );
		}
		
	}
	
	
	
	
}
