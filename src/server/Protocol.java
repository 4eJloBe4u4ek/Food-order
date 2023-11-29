package server;

interface CMD {
	static final byte CMD_CONNECT = 1;
	static final byte CMD_DISCONNECT = 2;
	static final byte CMD_USER = 3;
	static final byte CMD_SHOW_ORDERS = 4;
	static final byte CMD_MAKE_ORDER	= 5;
}

interface RESULT {
	static final int RESULT_CODE_OK 	= 0;
	static final int RESULT_CODE_ERROR 	= -1;
}	

interface PORT {
	static final int PORT = 8071;
}

public class Protocol implements CMD, RESULT, PORT {
	private static final byte CMD_MIN = CMD_CONNECT;
	private static final byte CMD_MAX = CMD_MAKE_ORDER;
	public static boolean validID( byte id ) {
		return id >= CMD_MIN && id <= CMD_MAX; 
	}
}

