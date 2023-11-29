package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageDisconnect extends Message implements Serializable {
		
	@Serial
	private static final long serialVersionUID = 1L;
	
	public MessageDisconnect() {
		super( Protocol.CMD_DISCONNECT );
	}
	
}

