import java.io.IOException;
import java.net.*;

public class Client {

	public static void main(String[] args) throws IOException{

		if (args.length != 5 && args.length != 4) {
			System.out.println("Usage: java Client <host_name> <port_number> <oper> <opnd>*");
		return;
			}


		DatagramSocket socket = new DatagramSocket();

		if (args[2].equals("REGISTER")){

			InetAddress address = InetAddress.getByName(args[0]);
			String owner = args[4];
			String plate = args[3];
			String message = plate+' '+owner;
			byte[] sbuf = message.getBytes();
			int port_number = Integer.parseInt(args[1]);
			DatagramPacket packet = new DatagramPacket (sbuf,sbuf.length, address, port_number);
			socket.send(packet);
		}

			else if (args[2].equals("LOOKUP")){

				InetAddress address = InetAddress.getByName(args[0]);
				String plate = args[3];
				String owner = null;
				String message = plate;
				byte[] sbuf = message.getBytes();
				int port_number = Integer.parseInt(args[1]);
				DatagramPacket packet = new DatagramPacket (sbuf,sbuf.length, address, port_number);
				socket.send(packet);
			}
			
	}	

	
}