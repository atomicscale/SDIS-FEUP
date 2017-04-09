package server;

import channels.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import SubProtocols.Backup;
        
public class Server implements IntServer {
    
	private static String cast;
	
	private ControlChannel cc;
	private RestoreChannel rc;
	private BackupChannel bc;
	
	private static String serverPort;
	private static String version;
	private static String peerID;
	
	private static int ccPort;
	private static int rcPort;
	private static int bcPort;
	
	private static InetAddress ccAddress;
	private static InetAddress rcAddress;
	private static InetAddress bcAddress;
	
	
    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }
    
    public void backup(File file, int degree) throws IOException{
      	System.out.println("Backup Started");
      	new Thread (new Backup(file, degree)).start();
    }
    public void restore(File file) throws IOException{
    	System.out.println("Restore Started");
    }
    public void delete(File file) throws IOException{
    	
    }
    
    public void reclaim(int space) throws IOException{
    	
    }
    
    public void state() throws IOException{
    	
    }   
    
    
    public static void main(String args[]) throws UnknownHostException {
    	
    	if(checkArguments(args)){
    	       try {
    	            Server obj = new Server();
    	            IntServer intServer = (IntServer) UnicastRemoteObject.exportObject(obj, 0);

    	            // Bind the remote object's stub in the registry
    	            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(serverPort));
    	            registry.rebind(serverPort,intServer);
    	            
    	          //  registry.rebind(cast, intServer);
    	            //STARTING CHANNELS ;
    	            obj.startChannels();
    	        
    	            
    	            System.err.println("Server running!");
    	        } catch (Exception e) {
    	            System.err.println("Server exception: " + e.toString());
    	            e.printStackTrace();
    	        }
    	}
    }
    
    private static boolean checkArguments(String[] args) throws UnknownHostException {
		if (args.length != 9) {
     	   System.err.println("Invalid server invocation");
			return false;
		}
		version = args[0];
		peerID = args[1];
		serverPort = args[2];
		ccAddress = InetAddress.getByName(args[3]);
		ccPort = Integer.parseInt(args[4]);
		bcAddress = InetAddress.getByName(args[5]);
		bcPort = Integer.parseInt(args[6]);
		rcAddress = InetAddress.getByName(args[7]);
		rcPort = Integer.parseInt(args[8]);
		return true;
		
	}

	private void startChannels(){
    	cc = new ControlChannel(ccPort,ccAddress, this);
    	new Thread(cc).start();
    	
    	rc = new RestoreChannel(rcPort,rcAddress, this);
    	new Thread(rc).start();
    	
    	bc = new BackupChannel(bcPort,bcAddress, this);
    	new Thread(bc).start();
    };
}