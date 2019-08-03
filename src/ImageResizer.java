
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import com.thebuzzmedia.imgscalr.Scalr;


public class ImageResizer {
	
	static ArrayList imageExtentsions = new ArrayList();
	
	static{
		imageExtentsions.add("jpg");
		imageExtentsions.add("jpeg");
		imageExtentsions.add("bmp");
		imageExtentsions.add("gif");
		imageExtentsions.add("JPG");
		imageExtentsions.add("JPEG");
	}
	
		static void createResizedCopy(String insrcFile,int pixel,String _outFile) throws IOException
		{
			long startTime = System.nanoTime();
			File srcFl = new File(insrcFile);
			if(isImage(srcFl)){
				BufferedImage srcImage = ImageIO.read(srcFl);
				// Scale the image using the imgscalr library
				BufferedImage scaledImage = Scalr.resize(srcImage, pixel);			
				File outFile = new File(_outFile);
				
			//	ImageIO.write(RenderedImage im,String formatName, File output);
				ImageIO.write(scaledImage,"jpg", outFile);
				long endTime = System.nanoTime();
				long diff = endTime - startTime;
				System.out.print("Resize  completed in : "+TimeUnit.NANOSECONDS.toSeconds(diff) +"  Secs" );
			}
			else {
				System.out.print("Not an Image ignoring....");
			}
		}

		static boolean isImage(File srcFl){
			String ext = FilenameUtils.getExtension(srcFl.getName());
			if(imageExtentsions.contains(ext)){
				return true;
			}
			else
				return false;
			
		}
	
	

}
