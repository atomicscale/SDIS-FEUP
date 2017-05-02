package subprotocols;

import java.net.DatagramPacket;

import message.Message;
import server.Server;

public class Restore  implements Runnable{
	
    private  DatagramPacket packet;
    
    private Server server;

    public Restore(DatagramPacket packet, Server server){
    	this.server = server;
    	this.packet = packet;
    }
    public DatagramPacket getPacket(){
        return this.packet;
    }
    
    public Server getServer(){
        return this.server;
    }
    
    public void HandleMessage(){

    	Message m = new Message(this.packet);
    	byte[] content = new byte[64000];
		content=m.getBody();
    	String chunkID = m.returnChunkNo();
    	String fileID = m.returnFileID();
    	String folder = "chunks";
    	System.out.println("Restoring");
    }
	@Override
	public void run() {
		HandleMessage();
		
	}
}
