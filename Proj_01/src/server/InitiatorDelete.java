package server;

public class InitiatorDelete implements Runnable{

	private Server server;
	private String filePath;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public InitiatorDelete(Server server, String file) {
		this.server = server;
		this.filePath = file;
	}
	
	
	
}
