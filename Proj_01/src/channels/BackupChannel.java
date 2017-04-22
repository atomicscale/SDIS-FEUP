package channels;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import message.Message;
import server.Server;
import subprotocols.Backup;

public class BackupChannel extends Channel {
	private byte[] buffer = new byte[65000];
	public BackupChannel(int port, InetAddress adress, Server server) {
		super(port, adress, server);
		// Auto-generated constructor stub
	}
	@Override
	public void run(){

		while(true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(packet);
				//System.out.println(new String(packet.getData()));
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			Message mR= new Message(packet);
		/*	if(mR.returnSenderID().equals(server.returnPeerID())){
				System.out.println("sameID");
			}
			else{*/
				new Thread(new Backup(packet,server)).start();
		//	}
		}
		
	}



}
