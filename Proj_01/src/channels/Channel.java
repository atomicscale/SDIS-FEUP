package channels;

import java.net.MulticastSocket;

import Message.Message;
import server.Server;

import java.net.DatagramPacket;
import java.net.InetAddress;

import java.io.IOException;

public class Channel implements Runnable{
	protected int port;
	private InetAddress address;
	protected MulticastSocket socket;
	protected static Server server;

	public Channel(int port, InetAddress address, Server server) {
		try {
			this.address = address;
			this.port = port;
			Channel.server = server;
			socket = new MulticastSocket(port);
			socket.joinGroup(address);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void sendData(){
		String str = "helloHai";
		System.out.println("In Sned Data");
		System.out.println(address);
		System.out.println(port);
		byte[] b = str.getBytes();
		DatagramPacket packet = new DatagramPacket(b, b.length, address, port);
		try{
			socket.send(packet);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

