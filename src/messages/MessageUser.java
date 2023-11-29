package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageUser extends Message implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public MessageUser() {
		super( Protocol.CMD_USER );
	}
}