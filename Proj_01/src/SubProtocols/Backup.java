package SubProtocols;

import java.io.File;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import server.Server;


public class Backup implements Runnable{

        private  DatagramPacket packet;
        
        private Server server;

        public Backup(DatagramPacket packet, Server server){
        	this.server = server;
        	this.packet = packet;
        }
        public DatagramPacket getPacket(){
            return this.packet;
        }
        
        public Server getServer(){
            return this.server;
        }


        public void PutChunk(){

        }
		@Override
		public void run() {
			// TODO Auto-generated method stub
		}
}