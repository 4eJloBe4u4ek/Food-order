package messages;

import server.Protocol;

import java.io.Serializable;

public class MessageConnect extends Message implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public String userFirstName;
	public String userLastName;
	
	public MessageConnect( String userNic, String userFullName ) {
		super( Protocol.CMD_CONNECT );
		this.userFirstName = userNic;
		this.userLastName = userFullName;
	}
	
}