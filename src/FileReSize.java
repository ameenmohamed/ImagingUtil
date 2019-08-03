
import java.io.File;
import java.io.IOException;




public class FileReSize extends BaseRecursion {

	public FileReSize(String loc) {
		super(loc);
	}
	public FileReSize(String loc,String destLoc) {
		super(loc,destLoc);
	}
	

	@Override
	public void executeDirTask(File fl) {
		String absSrcDirPath = fl.getAbsolutePath();
		String _destDir = absSrcDirPath.replace(srcDir,destdir); // destdir/+ filepath
		
		File destDir = new File(_destDir);
		if(!destDir.exists() && !(destDir.isHidden()))	{
			destDir.mkdirs();
		}
		startDigging(fl);
	}

	@Override
	public void executeFileTask(File flSrcDir) {
				
		String absSrcFilePath = flSrcDir.getAbsolutePath();
		String _destFile = absSrcFilePath.replace(srcDir,destdir); // destdir/+ filepath
		System.out.println("Replace result :"+_destFile);
		
		File fldestDir = new File(_destFile);
		
		if(!fldestDir.getParentFile().exists() && !(fldestDir.getParentFile().isHidden())){
			fldestDir.getParentFile().mkdirs();
		}
		
		System.out.println("resizing Src fl :"+absSrcFilePath);
		try {
			ImageResizer.createResizedCopy(absSrcFilePath, 1550, _destFile);
			//System.out.println("DestFileName :"+_destFile);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		System.out.println(".... Done");

	}

}
