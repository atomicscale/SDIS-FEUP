package FileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Chunk {
    private File file;
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

    public byte[] getData(){
        try {
           file = new File("myFile");
            FileInputStream is = new FileInputStream(file);
            byte[] data = new byte[CHUNK_MAX_SIZE];

            is.read(data);

            is.close();

        } catch (FileNotFoundException fnfE) {
            // file not found, handle case
        } catch (IOException ioE) {
            // problem reading, handle case
        }

        return data;
    }
}