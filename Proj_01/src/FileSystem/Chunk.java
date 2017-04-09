package FileSystem;

import java.io.FileOutputStream;
import java.io.IOException;

public class Chunk {
    private String fileId;
    private int chunkNo;
    private int replicationDeg;
    private byte[] data;

    public Chunk (String fileId, int chunkNo, int replicationDeg,byte[] data){
        this.fileId = fileId;
        this.chunkNo = chunkNo;
        this.replicationDeg = replicationDeg;
        this.data = data;
    }

    public Chunk (int chunkNo, int size){
        this.data = new byte[size];
        this.chunkNo = chunkNo;
    }

    public Chunk (int chunkNo, byte[] data){
        this.data = data;
        this.chunkNo = chunkNo;
    }

    public String getFileId() {
        return fileId;
    }

    public int getChunkNo() {
        return chunkNo;
    }

    public int getReplicationDeg() {
        return replicationDeg;
    }

    public void incReplicationDeg() {
        replicationDeg = replicationDeg + 1;
    }

    public void decReplicationDeg(){
        replicationDeg = replicationDeg - 1;
    }

    public byte[] getData(){ return data;}

    public void saveChunk(String fileName){

        try {
            FileOutputStream os = new FileOutputStream("ChunkFolder/" + fileName + "." + chunkNo);
            os.write(data);

            os.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}