package server;

import files.FileSet;
import message.Message;

import java.io.File;

public class InitiatorDelete implements Runnable{

	private Server server;
	private String filePath;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (server.returnManager().checkFile(filePath)) {
			File f = new File(filePath);
			FileSet fs = new FileSet(f, 0);

			SendDeleteMessage(fs.getFileID(),server.returnPeerID());
		}
	}
	
	public InitiatorDelete(Server server, String file) {
		this.server = server;
		this.filePath = file;
	}

	public void SendDeleteMessage(String fileID, String senderID){
		Message m = new Message("DELETE",""+1,senderID,fileID);
		m.createHeader();
		m.setBody(null);
		byte[] packet = m.setFullMessage();
		server.getControl().sendData(packet);
	}
	
	
}
