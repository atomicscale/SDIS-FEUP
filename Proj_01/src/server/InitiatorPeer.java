package server;

import java.io.File;

public class InitiatorPeer implements Runnable {
	
	private static final int ATTEMPTS = 5;
	
	
	private Server server;
	private File filePath;
	private int replicationDegree;
	

	public InitiatorPeer(Server server, File file, int replicationdegree) {
		this.server = server;
		this.filePath = file;
		this.replicationDegree = replicationdegree;
	}

	@Override
	public void run() {
		System.out.println("Initiator");
		sendPackage();
		
	}

	
	private void sendPackage() {
		server.getBackup().sendData();
		/*int attempts = 0;
		String chunkKey = putchunk.getFileId() + putchunk.getChunkNo();
		
		System.out.println(putchunk.getFileId());
		
		peer.getDB().saveChunkInfo(chunkKey, new ChunkData(chunkKey, 0, putchunk.getReplicationDeg(), putchunk.getData().length, putchunk.getFileId(), putchunk.getChunkNo()));
			
		while (attempts <= MAX_TRIES) {
			peer.getBackupChannel().sendMessage(putchunk);
			
			try {
				Thread.sleep(1000*attempts);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (peer.getDB().desiredReplication(chunkKey)) {
				return;
			}

			attempts++;
		}
		peer.getFs().saveDatabase(peer.getDB()); //TODO
		*/
	}
	
}
