package server;
import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntServer extends Remote {
	String sayHello() throws RemoteException;

	void backup(File file, int degree) throws IOException;

	void restore(File file) throws IOException;

	void delete(File file) throws IOException;

	void reclaim(int space) throws IOException;

	void state() throws IOException;
}

