package Message;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Message {
    public static int CHUNK_MAX_SIZE = 64000;

    private String header;
    private byte[] body;


    public Message() {

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

    public String generateHash(String fileID){
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(fileID.getBytes(StandardCharsets.UTF_8));

        //TODO from hash to String;
    }
}
