
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

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
	
		static void createResizedCopy(String insrcFile,int pixel,String _outFile) throws IOException{
			long startTime = System.nanoTime();
			File srcFl = new File(insrcFile);
			FileReSize.srcFileSize += srcFl.length() / 1024;
			if(isImage(srcFl)){
				BufferedImage srcImage = ImageIO.read(srcFl);
				// Scale the image using the imgscalr library
				BufferedImage scaledImage = Scalr.resize(srcImage, pixel);			
				File outFile = new File(_outFile);
				
			//	ImageIO.write(RenderedImage im,String formatName, File output);
				ImageIO.write(scaledImage,"jpg", outFile);
				long endTime = System.nanoTime();
				long diff = endTime - startTime;
				System.out.println("Resize  completed in : "+TimeUnit.NANOSECONDS.toMillis(diff) +"  Secs" );
				 FileReSize.destFileSize += outFile.length() /1024;
			}
			else {
				System.out.print("Not an Image ignoring....");
			}
		}
		
		static void jdkCompress(String insrcFile,float quality,String _outFile) throws IOException{
			long startTime = System.nanoTime();
			File imageFile = new File(insrcFile);
			FileReSize.srcFileSize += imageFile.length() / 1024;
	        File compressedImageFile = new File(_outFile);
	 
	        InputStream is = new FileInputStream(imageFile);
	        OutputStream os = new FileOutputStream(compressedImageFile);
	 
	        quality = 0.5f;
	 
	        // create a BufferedImage as the result of decoding the supplied InputStream
	        BufferedImage image = ImageIO.read(is);
	 
	        // get all image writers for JPG format
	        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
	 
	        if (!writers.hasNext())
	            throw new IllegalStateException("No writers found");
	 
	        ImageWriter writer = (ImageWriter) writers.next();
	        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	        writer.setOutput(ios);
	 
	        ImageWriteParam param = writer.getDefaultWriteParam();
	 
	        // compress to a given quality
	        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	        param.setCompressionQuality(quality);
	 
	        // appends a complete image stream containing a single image and
	        //associated stream and image metadata and thumbnails to the output
	        writer.write(null, new IIOImage(image, null, null), param);
	 
	        long endTime = System.nanoTime();
			long diff = endTime - startTime;
			System.out.println("Resize  completed in : "+TimeUnit.NANOSECONDS.toMillis(diff) +"  Secs" );
	        
	        // close all streams
	        is.close();
	        os.close();
	        ios.close();
	        writer.dispose();
	        FileReSize.destFileSize += compressedImageFile.length() /1024;
			
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
