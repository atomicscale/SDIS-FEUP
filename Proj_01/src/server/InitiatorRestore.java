package server;

import java.io.File;

import files.FileSet;
import message.Message;

public class InitiatorRestore implements Runnable {

	private Server server;
	private String filePath;

	@Override
	public void run() {

		
		if (server.returnManager().checkFile(filePath)) {
			File f = new File(filePath);
			FileSet fs = new FileSet(f, 0);
			
			SendRestoreMessage(fs.getFileID(),server.returnPeerID());
		}
		
	}
	
	public InitiatorRestore(Server server, String file) {
		this.server = server;
		this.filePath = file;
	}
	
	public void SendRestoreMessage(String fileID, String senderID){
		Message m = new Message("GETCHUNK",""+1,senderID,fileID);
		m.createHeader();
		m.setBody(null);
		byte[] packet = m.setFullMessage();
		server.getRestore().sendData(packet);
	};

}
