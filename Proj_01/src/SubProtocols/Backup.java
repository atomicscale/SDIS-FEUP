package SubProtocols;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Backup implements Runnable{

        private File file;
        private int replication;

        public Backup(File f, int replicationDeg){
          file = f;
          replication = replicationDeg;
        }
        public int getReplication(){
            return replication;
        }

        public void PutChunk(){

        }
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("hello");
		}
}