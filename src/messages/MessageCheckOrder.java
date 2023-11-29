package messages;

import server.Protocol;

import java.io.Serial;
import java.io.Serializable;

public class MessageCheckOrder extends Message implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public MessageCheckOrder() {
		super( Protocol.CMD_SHOW_ORDERS );
	}
}
