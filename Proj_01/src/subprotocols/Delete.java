package subprotocols;

import java.io.IOException;
import java.net.DatagramPacket;

import server.Server;
import message.Message;


import java.io.File;

public class Delete implements Runnable{

    private  DatagramPacket packet;
    
    private Server server;

    public Delete(DatagramPacket packet, Server server){
    	this.server = server;
    	this.packet = packet;
    }
    public DatagramPacket getPacket(){
        return this.packet;
    }
    
    public Server getServer(){
        return this.server;
    }

    public void DeleteChunks(File file, String fileId) {
        if(file.isDirectory()){

            //directory is empty
            if(file.list().length==0){

                System.out.println("Directory is empty : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    DeleteChunks(fileDelete,fileId);
                }

            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    public void HandleMessage(){
        Message m = new Message(this.packet);
        String fileId = m.returnChunkNo();
        String folder = "chunks";
        File file = new File(folder);
        DeleteChunks(file,fileId);
    }

    public void Delete(){
        System.out.print("Content Deleted");
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub

        HandleMessage();
        Delete();

	}
}
