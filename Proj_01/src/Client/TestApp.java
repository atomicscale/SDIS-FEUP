package Client;

import java.net.UnknownHostException;

public class TestApp{

    public static void main(String[] args) throws UnknownHostException{
    	int argsize = args.length;
    	
    	if(argsize < 2 || argsize > 4){
    		System.out.println( "Usage $java TestApp <peer_ap> <sub_protocol> <opnd_1> <opnd_2> ");
    		System.out.println("where:");
    		System.out.println("<peer_ap> Is the peer's access point.");
    		System.out.println("<sub_protocol> Is the operation the peer of the backup service must execute.");
    		System.out.println("<opnd_1> Is either the path name of the file to backup/restore/delete, or the amount of space to reclaim (in KByte).");
    		System.out.println("<opnd_2> Backup only, is an integer that specifies the desired replication degree.");
    	}
    	else{
    		System.out.println("Working...\n");
    		
    		String Protocol = args[1];
    		String[] recievedData = new String[args.length];
     		switch(Protocol){
    		case "BACKUP":
    			recievedData[0] = args[2];
    			recievedData[1] = args[3];
    			break;	
    		case "RESTORE":
    			//recievedData[0] = args[2];
    			break;	
    		case "DELETE":
    			recievedData[0] = args[2];
    			break;	
    		case "RECLAIM":
    			recievedData[0] = args[2];	
    			break;   			
    		} 		
    	}
    }
}