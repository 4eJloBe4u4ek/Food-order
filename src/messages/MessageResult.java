package messages;

import java.io.Serial;
import java.io.Serializable;
import server.Protocol;

public class MessageResult extends Message implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}
	public boolean Error() {
		return errorCode != Protocol.RESULT_CODE_OK;
	}
	
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	
	protected MessageResult() {
		super();
	}
	
	protected MessageResult( byte id, String errorMessage ) {
		
		super( id );
		this.errorCode = Protocol.RESULT_CODE_ERROR;
		this.errorMessage = errorMessage;
	}

	protected MessageResult( byte id ) {
		
		super( id );
		this.errorCode = Protocol.RESULT_CODE_OK;
		this.errorMessage = "";
	}
}