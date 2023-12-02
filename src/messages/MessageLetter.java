package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageLetter extends Message implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public String userFullName;
	public String txt;
	
	public MessageLetter( String userFullName, String txt ) {
		
		super( Protocol.CMD_MAKE_ORDER );
		this.userFullName = userFullName;
		this.txt = txt;
	}
	
}