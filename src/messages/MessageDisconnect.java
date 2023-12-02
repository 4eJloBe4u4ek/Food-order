package messages;

import server.Protocol;

import java.io.Serial;
import java.io.Serializable;

public class MessageDisconnect extends Message implements Serializable {
		
	@Serial
	private static final long serialVersionUID = 1L;
	
	public MessageDisconnect() {
		super( Protocol.CMD_DISCONNECT );
	}
	
}

