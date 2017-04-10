package Message;

import java.util.EnumMap;

public final class HeaderElems {
	public EnumMap<Elems, String> fields;
	
	public enum Elems {
		TYPE,
		VERSION,
		SENDERID,
		FILEID,
		CHUNKNO,
		REPLICATIONDEG
	}
	
	public EnumMap<Elems, String> startElems(){
		return new EnumMap<Elems, String>(Elems.class);
	}
}
