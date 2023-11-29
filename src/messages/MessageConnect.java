package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageConnect extends Message implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public String userNic;
	public String userFullName;
	
	public MessageConnect( String userNic, String userFullName ) {
		super( Protocol.CMD_CONNECT );
		this.userNic = userNic;
		this.userFullName = userFullName;
	}
	
}