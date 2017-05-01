package message;

import java.net.DatagramPacket;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.EnumMap;


public class Message {
    public static final int CHUNK_MAX_SIZE = 64000;
    public static final String CRLF = "\r\n";
    public static final String Space = " ";
    private String header;
    private byte[] body;
    private byte[] message;
    
	
	
    public Message(){}
    
    
private EnumMap<Elems, String> elems;
	
	public enum Elems {
		TYPE,
		VERSION,
		SENDERID,
		FILEID,
		CHUNKNO,
		REPLICATIONDEG
	}
	
	
    public Message(String header, byte[] body) {
        this.header = header;
        this.body = body;
    }
    
    public Message(DatagramPacket packet) {
    	elems = new EnumMap<Elems, String>(Elems.class);
    	message = packet.getData();
    	String toSplit = new String(packet.getData(), packet.getOffset(),packet.getLength());
    	
    	splitPacket(toSplit);
    	readHeader();
    	
    }
    
    
    public Message(String messageType, String version, String senderId, String fileId, String chunkNo, String replicationDeg){
    	elems = new EnumMap<Elems, String>(Elems.class);
    	
    	elems.put(Elems.TYPE, messageType);
        elems.put(Elems.VERSION, version);
        elems.put(Elems.SENDERID, senderId);
        elems.put(Elems.FILEID, fileId);
        elems.put(Elems.CHUNKNO, chunkNo);
        elems.put(Elems.REPLICATIONDEG, replicationDeg);
        
    }

	public Message(String messageType, String version, String senderId, String fileId){
		elems = new EnumMap<Elems, String>(Elems.class);

		elems.put(Elems.TYPE, messageType);
		elems.put(Elems.VERSION, version);
		elems.put(Elems.SENDERID, senderId);
		elems.put(Elems.FILEID, fileId);
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
    
    public String returnSenderID() {

        return elems.get(Elems.SENDERID);
    }
    
    public String returnChunkNo() {

        return elems.get(Elems.CHUNKNO);
    }

	public String returnFileID() {

		return elems.get(Elems.FILEID);
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

		for(int i = 0; i < vals.length; i++){
			elems.put(Elems.values()[i], vals[i]);
		}	
	};
	
	public void createHeader(){
		this.header = "";
		for(Elems elem : Elems.values()){
			String currElem = elems.get(elem);
			if(currElem != null){
				if(elem ==Elems.TYPE){
					this.header += currElem;
					
				}
				else{
					this.header +=" " + currElem;
				}
			}
		}
		this.header += Message.CRLF+Message.CRLF;
		System.out.println(this.header);
	};
	
	
	public byte[] setFullMessage(){
		byte[] header = this.header.getBytes();
		byte[] fullMessage = new byte[header.length + this.body.length];
		
		
	    System.arraycopy(header, 0, fullMessage, 0, header.length);
	    System.arraycopy(this.body, 0, fullMessage, header.length, this.body.length);
	    
	    return fullMessage;
	}
}
