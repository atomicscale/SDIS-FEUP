package subprotocols;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import message.Message;
import server.Server;

public class Backup implements Runnable{

        private  DatagramPacket packet;
        
        private Server server;

        public Backup(DatagramPacket packet, Server server){
        	this.server = server;
        	this.packet = packet;
        }
        public DatagramPacket getPacket(){
            return this.packet;
        }
        
        public Server getServer(){
            return this.server;
        }

        public boolean SaveChunk(byte[]content,String chunkID,String fileID, String folder){
        	int foo = Integer.parseInt(chunkID);
        	
        	File newFile = new File(folder,String.format("%06d", foo));
        	try {
        		FileOutputStream out = new FileOutputStream(newFile + "." + fileID);
        		out.write(content, 0, content.length);
        		out.close();
        	} catch (Exception e) {
        		System.err.println("Error: Writting File " + e.getMessage());
        		return false;
        	}
        	return true;
        }
        
        public void PutChunk(){
        	System.out.println("I am Storing");
        }
        
        public void HandleMessage(){

        	Message m = new Message(this.packet);
        	byte[] content = new byte[64000];
			content=m.getBody();
        	String chunkID = m.returnChunkNo();
        	String fileID = m.returnFileID();
        	String folder = "chunks";
        	SaveChunk(content,chunkID, fileID, folder);
        }
        
		@Override
		public void run() {
			HandleMessage();
			PutChunk();
		}
}