package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageCheckOrdersResult extends MessageResult implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public String[] letters = null;
	
	public MessageCheckOrdersResult(String errorMessage ) { //Error
		super( Protocol.CMD_SHOW_ORDERS, errorMessage );
	}

	public MessageCheckOrdersResult(String[] letters ) { // No errors
		super( Protocol.CMD_SHOW_ORDERS );
		this.letters = letters;
	}

}
