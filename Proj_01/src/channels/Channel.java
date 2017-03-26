package channels;

import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

import java.io.IOException;

public class Channel {
	private int port;
	private InetAddress address;
	private MulticastSocket socket;
	
	public Channel(int port, InetAddress adress){
		try{
			this.address = address;
			this.port = port;
			socket = new MulticastSocket(port);
			socket.joinGroup(address);			
		}
		catch(IOException e){
		}

	}
	}
