package server;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements IntRemote {
    
	private static String cast;
	
	
    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }
    
    public void backup(File file, int degree) throws IOException{
    	
    }
    public void restore(File file) throws IOException{
    	
    }
    public void delete(File file) throws IOException{
    	
    }
    
    public void reclaim(int space) throws IOException{
    	
    }
    
    public void state() throws IOException{
    	
    }   
    
    
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            IntRemote stub = (IntRemote) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            //Should implement validation again here maybe!??
            cast = args[1];
            
            registry.rebind(cast, stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}