package Message;

import java.net.DatagramPacket;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.EnumMap;

import Message.HeaderElems.Elems;


public class Message {
    public static final int CHUNK_MAX_SIZE = 64000;
    public static final String CRLF = "\r\n";
    public static final String Space = " ";
    private String header;
    private byte[] body;
    private byte[] message;
    
	EnumMap<Elems, String> elems;
  
    
    public Message(){
        //TODO general message encoding
    }

    public Message(String header, byte[] body) {
        this.header = header;
        this.body = body;
    }
    
    public Message(DatagramPacket packet) {
    	message = packet.getData();
    	String toSplit = new String(packet.getData(), packet.getOffset(),packet.getLength());
    	
    	splitPacket(toSplit);
    	readHeader();
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

    public byte[] getMessage(){
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
    private void splitPacket (String toSplit){
    	String[] splitted = toSplit.split(Message.CRLF+Message.CRLF,2);
    	setHeader(splitted[0]);
    	setBody(Arrays.copyOfRange(message, getHeader().length()+4, message.length));
   
    }
    
	private void readHeader() {
		String[] vals = this.header.split("\\s+");	
		elems= new HeaderElems().startElems();

		for(int i = 0; i < vals.length; i++){
			elems.put(Elems.values()[i], vals[i]);
		}
	
		
	};
}