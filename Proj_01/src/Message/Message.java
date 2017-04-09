package Message;

import java.security.MessageDigest;

public class Message {
    public static final int CHUNK_MAX_SIZE = 64000;
    public static final String CRLF = "\r\n";
    public static final String Space = " ";
    private String header;
    private byte[] body;
    private String message;

    public Message(){
        //TODO general message encoding
    }

    public Message(String header, byte[] body) {
        this.header = header;
        this.body = body;

        if (body == null){ // Stored
            this.message = this.header + this.CRLF + this.CRLF;
        }
        else { //Putchunk
            this.message = this.header + this.CRLF + this.CRLF + this.body.toString();
        }
    }
    public String Header(String messageType, String version, String senderId, String fileId, String chunkNo, String replicationDeg){
        if (replicationDeg == null && replicationDeg.isEmpty()){
            return messageType + Space + version + Space + senderId + Space + fileId + Space + chunkNo;
        }
        else if (chunkNo == null && chunkNo.isEmpty()){
            return messageType + Space + version + Space + senderId + Space + fileId;
        }
        return messageType + Space + version + Space + senderId + Space + fileId + Space + chunkNo + Space + replicationDeg;
    }

    public String getMessage(){
        return message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {

        this.header = header;
    }

    public byte[] getBody() {

        return body;
    }

    public void setBody(byte[] body) {

        this.body = body;
    }

    public static String getSha256(String fileID) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(fileID.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
