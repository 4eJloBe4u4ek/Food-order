package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageLetter extends Message implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	public String usrNic;
	public String txt;
	
	public MessageLetter( String usrNic, String txt ) {
		
		super( Protocol.CMD_LETTER );
		this.usrNic = usrNic;
		this.txt = txt;
	}
	
}