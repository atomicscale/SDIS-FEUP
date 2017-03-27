package FileSystem;

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


}