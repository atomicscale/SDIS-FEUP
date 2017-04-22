package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Vector;

import files.Chunk;
import files.FileSet;
import message.Message;

public class InitiatorPeer implements Runnable {
	
	private static final int ATTEMPTS = 5;
	private int currReplication = 0;

	private byte[] buffer = new byte[64000];

	private Vector<Chunk> chunks = new Vector<Chunk>();

	private Server server;
	private String filePath;
	private int replicationDegree;
	
	private Chunk chunk;

	public InitiatorPeer(Server server, String file, int replicationdegree) {
		this.server = server;
		this.filePath = file;
		this.replicationDegree = replicationdegree;
	}

	@Override
	public void run() {
		System.out.println("Initiator");
		
		if (chunk == null){
			System.out.println("chunk null");
			startFile();
		}
		else{
			if(true){
				System.out.println("chunk not null");
				startFile();
			}
		}
		
	}
	
	private void startChunk(){
		
	};
	
	private void startFile() {
		
		if (server.returnManager().checkFile(filePath)) {
			File f = new File(filePath);
			FileSet fs = new FileSet(f, replicationDegree);

			try (FileInputStream file = new FileInputStream(f)) {

				int byteSize;
				int fileavailable = file.available();
				for(int i= 0; fileavailable > 0; i++ ) {
					if (fileavailable > Message.CHUNK_MAX_SIZE)
						byteSize = Message.CHUNK_MAX_SIZE;
					else
						byteSize = fileavailable;

					Chunk chunk = new Chunk(i, byteSize);
					chunks.add(chunk);

					file.read(chunk.getData());

					fileavailable = file.available();
				}
					file.close();

				for (int j = 0; j < chunks.size(); j++) {
					System.out.print(chunks.size()+("asddddddddddddddddddddddddddddddddddddddddddddddd"));
					sendChunk(fs.getReplicationDegree(), fs.getFileID(), server.returnPeerID(), fs.getChunkID(), chunks.get(j).getData(), 1);
					try{

						Thread.sleep(1000);
					}catch(InterruptedException ex){
						//do stuff
					}
					fs.incChunkID();
				}


			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else{
			System.out.println("File doesn't exist");
		}
	};
	
	
	private void sendChunk(int replication, String fileID, String senderID, int chunkID, byte[] message, int readValue) {
		Message m = new Message("PUTCHUNK",""+1,senderID,fileID,""+chunkID,""+replication);
		m.createHeader();
		m.setBody(message);
		byte[] packet = m.setFullMessage();
		server.getBackup().sendData(packet);

	}

	//for 2 chunk++;
	public InitiatorPeer(Server server, Chunk chunk) {
		this.server = server;
		this.chunk = chunk;
	}
}
