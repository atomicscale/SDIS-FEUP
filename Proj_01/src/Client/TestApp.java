package Client;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.IntRemote;

public class TestApp {
	
	private static IntRemote stub;

	public static void main(String[] args) throws NumberFormatException, IOException {
		if(checkMethod(args)){
			System.out.println("where:");
			startPeer(args[0]);
			System.out.println("where2:");
			runPeer(args);
		}
	}

	private static boolean checkMethod(String args[]) {
		int argsize = args.length;

		if (argsize < 2 || argsize > 4) {
			System.out.println("Usage $java TestApp <peer_ap> <sub_protocol> <opnd_1> <opnd_2> ");
			System.out.println("where:");
			System.out.println("<peer_ap> Is the peer's access point.");
			System.out.println("<sub_protocol> Is the operation the peer of the backup service must execute.");
			System.out.println(
					"<opnd_1> Is either the path name of the file to backup/restore/delete, or the amount of space to reclaim (in KByte).");
			System.out.println("<opnd_2> Backup only, is an integer that specifies the desired replication degree.");
			return false;
		} else {
			switch (args[1].toLowerCase()) {
			case "backup":
				File file = new File(args[2]);
				if (!file.exists() || file.isDirectory() || argsize != 4) {
					return false;
				} else
					return true;
			case "restore":
				if (argsize != 3) {
					return false;
				}
				break;
			case "delete":
				if (argsize != 3) {
					return false;
				}
				break;
			case "reclaim":
				if (argsize != 3) {
					return false;
				}
				break;
			case "state":
				if (argsize != 2) {
					return false;
				}
				break;
			default:
				return false;
			}
			return true;
		}
	}

	private static void startPeer(String lookup) {
		try {
			Registry registry = LocateRegistry.getRegistry();
			IntRemote stub = (IntRemote) registry.lookup(lookup);
			String awnser = stub.sayHello(); //this is for testing
			System.out.println("Awnser: " + awnser);//this is for testing
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	private static void runPeer(String args[]) throws NumberFormatException, IOException {
		String Protocol = args[1];
		switch (Protocol.toLowerCase()) {
		//this is not well done eu tenho que arranjar uma maneira de verificar os args uma so vez aqui e no peer
		case "backup":
			File f = new File(args[2]);
			stub.backup(f,Integer.parseInt(args[3]));
			break;
		case "restore":
			File r = new File(args[2]);
			stub.restore(r);
			break;	
		case "delete":
			File d = new File(args[2]);
			stub.restore(d);
			break;
		case "reclaim":
			stub.reclaim(Integer.parseInt(args[2]));
			break;
		case "state":
			stub.state();
			break;
		default:
			break;
		}
	}
	

}