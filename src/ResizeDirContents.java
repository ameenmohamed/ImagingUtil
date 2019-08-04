import java.util.concurrent.TimeUnit;



public class ResizeDirContents {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String srcdir = "/Users/mohamedameen/Downloads/gausiaacademy/assets/images";
		String destdir = "/Users/mohamedameen/amroot/workout/gajdk";
		long startTime = System.nanoTime();	
		FileReSize fs = new FileReSize(srcdir,destdir);
		long endTime = System.nanoTime();
		long totTime = endTime - startTime;
		System.out.println("Time to resize "+TimeUnit.NANOSECONDS.toSeconds(totTime) +" seconds" );
		System.out.println("File Processed"+ FileReSize.fileCount);
	
		System.out.println("Size reduction :"+((FileReSize.srcFileSize-FileReSize.destFileSize)/1024)+ " MB");
		System.out.println("Compression percentage :"+ ((FileReSize.destFileSize / FileReSize.srcFileSize)/100) );

	}

}
