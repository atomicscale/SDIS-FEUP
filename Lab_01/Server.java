import java.io.IOException;
import java.net.*;

public class Server {
	
	public static void main(String[] args) throws IOException{
		if (args.length != 2) {
			System.out.println("Usage: java Echo <hostname> <string to echo>");
		return;
		}

	DatagramSocket socket = new DatagramSocket();
	byte[] buffer = args[1].getBytes();
	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	System.out.println("Receiving data...");
	socket.receive(packet);

	String received = new String(packet.getData());
	System.out.println("Echoed Message: " + received);
	socket.close();
	
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