import java.io.IOException;
import java.net.*;

public class Client {
	
	public static void main(String[] args) throws IOException{
		if (args.length != 2) {
			System.out.println("Usage: java Echo <hostname> <string to echo>");
		return;
		}

		DatagramSocket socket = new DatagramSocket();
		byte[] sbuf = args[1].getBytes();
		InetAddress address = InetAddress.getByName(args[0]);
		System.out.println(address);
		System.out.println(sbuf);
		
		DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, address, 9999);
		socket.send(packet);
	
	}
}