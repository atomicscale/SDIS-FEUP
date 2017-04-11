package channels;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import server.Server;
import subProtocols.Backup;

public class RestoreChannel extends Channel {
	private byte[] buffer = new byte[65000];
	public RestoreChannel(int port, InetAddress adress, Server server) {
		super(port, adress, server);
		// Auto-generated constructor stub
	}
	@Override
	public void run(){

		while(true){
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(packet);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		//	new Thread (new Backup(null, port)).start();
		}
		
	}
	
	

}
