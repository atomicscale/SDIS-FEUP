package channels;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import SubProtocols.Backup;
import server.Server;

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
				System.out.println(new String(packet.getData()));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new Backup(packet,server)).start();
		}
		
	}



}
