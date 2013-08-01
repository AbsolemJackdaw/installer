package installer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

/**
 * Simple Java program to copy files from one directory to another directory.
 * Java IO API doesn't provide any direct way to copy files but you can copy files
 * by copying its contents from InputStream to OutputStream. Though there are some
 * better ways to do it like Using Apache Commons Utils library has FileUtils class
 * to copy files in Java
 *
 * @author Javin
 */
public class MainClass {

	private static File target;
	private static File source;
	private static File backupTarget;

	private static Date date = new Date();

	public MainClass(String absoluthePath, String backUpPath, String targetPath){
		//absolute path for source file to be copied
		String src = absoluthePath;
		//directory where file will be copied
		String trgt = targetPath;
		
		setSourceAndTarget(src,backUpPath, trgt);
		backUpModDirectory(target, backupTarget);
		deleteModDirectory(target);
		copyfiles(source, target);
		System.exit(0);
	}  

	public static void setSourceAndTarget(String sourcePath, String backupPath,String targetPath){
		File sourceFile = new File(sourcePath);
		//creates a backup folder with a folder of the very accurate time and date of when the backup was created
		File backUpFile = new File(backupPath + "/minecraft_mod_backup/"+getDate()+"/");
		File targetFile = new File(targetPath);

		source = sourceFile;
		target = targetFile;
		backupTarget = backUpFile;

		System.out.println("Determinating directories. SourcePath = "+sourceFile+". backUpPath = "+backUpFile+". targetPath = "+targetFile);
	}

	public static void copyfiles(File source, File target){
		//copy file from one location to other
		try {
			FileUtils.copyDirectory(source, target);
			System.out.println("Copying of file from Java program is completed");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("failed to copy source to target");
		}
	}

	public static void deleteModDirectory(File oldDirectory){
		try {
			FileUtils.cleanDirectory(oldDirectory);
			System.out.println("Cleaned old mods folder in "+oldDirectory.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to delete old mod folder in "+oldDirectory.getAbsolutePath());

		}
	}

	public static void backUpModDirectory(File source, File target){
		try {
			FileUtils.copyDirectory(source, target);
			System.out.println("Succesfully created backup of old Mods folder");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("failed to create backup of old mods !! ");
		}
	}

	private static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date);
	}
}
