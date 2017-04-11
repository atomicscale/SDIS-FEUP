package files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import message.Message;
import server.Server;

public class FileSet {
    private String fileID;
    public String name;
    public float fileSize;
    private String version = "1.0";
    private String owner;
    private int chunkID = 0;
    private long lastModified;
    private int repDegree;
    byte[] buffer = new byte[64000];
    
    public void setChunkID(int ID){
    	this.chunkID = ID;
    };
    
    public String getName() {
        return name;
    }

    public float getFileSize() {
        return fileSize;
    }
    
    public int getReplicationDegree() {
        return repDegree;
    }
    
    public String getFileID() {
        return fileID;
    }
    public int getChunkID() {
        return chunkID;
    }
    
    public void incChunkID() {
        this.chunkID++;
    }
    
    public String getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    
    
    public FileSet(String fileName, float fileSize, int chunkID, int repDegree, long lastModified, String fileID, String owner ){
        super();
		this.name = fileName;
		this.fileSize = fileSize;
		this.owner = owner;
		this.lastModified = lastModified;
		this.chunkID = chunkID;
		this.repDegree = repDegree;
		this.fileID = fileID;

    }
    
    public FileSet(File f, int replication){
        this.name = f.getName();
        this.fileSize = f.length();
		Path path = Paths.get(f.getPath());
		
		try {
			this.owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS).getName();
		} catch (IOException e) {
			this.owner = null;
		}
		this.chunkID = 0;			
		this.lastModified  = f.lastModified();
		this.repDegree = replication;
		
		try {
			String toDigest = name + owner + lastModified + "" + fileSize;
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] temp = digest.digest(toDigest.getBytes(StandardCharsets.UTF_8));
			
			fileID = DatatypeConverter.printHexBinary(temp);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();	
		}
    }
}
    
