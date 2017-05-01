package channels;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import message.Message;
import server.Server;
import subprotocols.Delete;

public class ControlChannel extends Channel {
	byte[] buffer = new byte[65000];
	
	
	public ControlChannel(int port, InetAddress adress, Server server) {
		super(port, adress, server);
		// Auto-generated constructor stub
	}
	
	public void run(){

		while(true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(packet);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			Message mR= new Message(packet);
		/*	if(mR.returnSenderID().equals(server.returnPeerID())){
				System.out.println("sameID");
			}
			else{*/
			new Thread(new Delete(packet,server)).start();

		}
		
	}
}
