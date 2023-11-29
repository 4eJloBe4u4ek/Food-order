package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageLetterResult extends MessageResult implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public MessageLetterResult( String errorMessage ) { //Error
		
		super( Protocol.CMD_MAKE_ORDER, errorMessage );
	}
	
	public MessageLetterResult() { // No errors
		
		super( Protocol.CMD_MAKE_ORDER );
	}
}