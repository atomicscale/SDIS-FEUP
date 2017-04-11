package filesManager;

import java.io.File;

public class Manager {
	
	public static  String  chunksDir;
	public static  String  filesDir;
	public static  String  backupDir;

	public Manager(){
		chunksDir = "chunks/";
		if(!checkDirectory(chunksDir)) {
			File file = new File(chunksDir);
			file.mkdir();
		}
		filesDir = "Files/";
		if(!checkDirectory(filesDir)) {
			File file = new File(filesDir);
			file.mkdir();
		}
		backupDir= "metadata/";
		if(!checkDirectory(backupDir)) {
			File file = new File(backupDir);
			file.mkdir();
		}
	}
	
	public boolean checkDirectory(String path) {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			return true;
		} else
			return false;
	}
	
	public boolean checkFile(String path){
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			return true;
		} else
			return false;
	}
}
