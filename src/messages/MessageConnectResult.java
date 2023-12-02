package messages;

import server.Protocol;

import java.io.Serial;
import java.io.Serializable;

public class MessageConnectResult extends MessageResult implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public MessageConnectResult( String errorMessage ) { // Error
		super( Protocol.CMD_CONNECT, errorMessage );
	}
	
	public MessageConnectResult() { // No error
		super( Protocol.CMD_CONNECT );
	}
}