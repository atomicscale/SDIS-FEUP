package subprotocols;

import java.net.DatagramPacket;

import server.Server;
import message.Message;

import java.io.File;

public class Delete implements Runnable{

    private  DatagramPacket packet;
    
    private Server server;

    public Delete(DatagramPacket packet, Server server){
    	this.server = server;
    	this.packet = packet;
    }
    public DatagramPacket getPacket(){
        return this.packet;
    }
    
    public Server getServer(){
        return this.server;
    }

    public void DeleteChunks(String folder){
        File file = new File(folder);
        String[] files = file.list();

        if (file.isDirectory() && (files.length > 0))
                file.delete();
    }

    public void HandleMessage(){
        String folder = "chunks";
        DeleteChunks(folder);
    }

    public void Delete(){
        System.out.print("Content Deleted");
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
        HandleMessage();
		Delete();
	}
}
