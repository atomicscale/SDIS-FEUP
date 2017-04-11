package files;

import java.io.FileOutputStream;
import java.io.IOException;

public class Chunk {
    private String fileId;
    private int chunkID;
    private int replicationDegGoal;
    private byte[] data;
    private String chunkRef;
    private int chunkLenght;
    private int replication;

    public Chunk (String chunkRef,int replication, int replicationDegGoal, int chunkLenght, String fileId, int chunkID){
        this.chunkRef = chunkRef;
        this.replication = replication;
        this.replicationDegGoal = replicationDegGoal;
        this.fileId = fileId;
        this.chunkLenght = chunkLenght;
    	this.fileId = fileId;
        this.chunkID = chunkID;      
    }

    //delete maybe?
    public Chunk (int chunkNo, int size){
        this.data = new byte[size];
        this.chunkID = chunkNo;
    }

    public Chunk (int chunkNo, byte[] data){
        this.data = data;
        this.chunkID = chunkNo;
    }

    public String getFileId() {
        return fileId;
    }

    public int getChunkID() {
        return chunkID;
    }
    
    public int getChunkLenght() {
        return chunkLenght;
    }

    public int getReplicationDegGoal() {
        return replicationDegGoal;
    }
    
    public int getReplication() {
        return replication;
    }

    public void incReplicationDeg() {
        replication = replication + 1;
    }

    public void decReplicationDeg(){
    	replication = replication - 1;
    }

    public byte[] getData(){ return data;}
}