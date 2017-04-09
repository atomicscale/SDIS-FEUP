package FileSystem;

import Message.Message;

import java.security.MessageDigest;

public class FileHash {
    public String name;
    public byte[] ID;
    public int fileSize;
    private String version = "1.0";

    public String getName() {
        return name;
    }

    public byte[] getID() {
        return ID;
    }

    public int getFileSize() {
        return fileSize;
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


    public FileHash(String fileName){
        this.name = fileName;

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(name.getBytes("ASCII"));
            ID = md.digest();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
