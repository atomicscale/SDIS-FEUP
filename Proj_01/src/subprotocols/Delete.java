package subprotocols;

import java.net.DatagramPacket;

import server.Server;

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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
