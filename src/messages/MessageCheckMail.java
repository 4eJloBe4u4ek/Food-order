package messages;

import server.Protocol;

import java.io.Serial;
import java.io.Serializable;

public class MessageCheckMail extends Message implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public MessageCheckMail() {
		super( Protocol.CMD_CHECK_MAIL );
	}
}
