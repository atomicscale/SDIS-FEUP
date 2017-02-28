import java.io.IOException;
import java.net.*;

public class Server {
	private DatagramSocket socket;
	private int SIZE = 1024;
	
	public static void main(String[] args) throws IOException{
		if (args.length != 1) {
			System.out.println("Usage: java Server <port_number>");
		return;
		}
	

	Server server=new Server(args[0]);	
	server.Ready();



	
	}
	
	public void Ready() throws IOException{

		DatagramPacket packet = new DatagramPacket(new byte[SIZE], SIZE);
		System.out.println("Receiving data...");
		socket.receive(packet);
		
		String received = new String(packet.getData());
		System.out.println("Echoed Message: " + received);
		socket.close();
	};
	
	
	public Server(String port) throws SocketException{
		System.out.println(port);
		this.socket=new DatagramSocket(Integer.parseInt(port));


	    }
}


// send request
/*	DatagramSocket socket = new DatagramSocket();
	byte[] sbuf = args[1].getBytes();
	InetAddress address = InetAddress.getByName(args[0]);
	DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, address, 4445);
	socket.send(packet);

// get response
	byte[] rbuf = new byte[sbuf.length];
	packet = new DatagramPacket(rbuf, rbuf.length);
	socket.receive(packet);
// display response
	String received = new String(packet.getData());
	System.out.println("Echoed Message: " + received);
		socket.close();
	}

	private int Lookup {
		System.out.println("Lookup:");

		if ()
	}

	private int Register {
		System.out.println("Register:")
	}
}

*/