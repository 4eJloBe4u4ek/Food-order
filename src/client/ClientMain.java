package client;

import messages.*;
import server.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.TreeMap;

public class ClientMain {
	// arguments: userNic userFullName [host]
	public static void main(String[] args)  {
		if (args.length < 2 || args.length > 3) {
			System.err.println(	"Invalid number of arguments\n" + "Use: first name and last name [host]" );
			waitKeyToStop();
			return;
		}
		try ( Socket sock = ( args.length == 2
				? new Socket( InetAddress.getLocalHost(), Protocol.PORT )
				: new Socket( args[2], Protocol.PORT ) )) {
			System.err.println("initialized");
			session(sock, args[0], args[1] );
		} catch ( Exception e) {
			System.err.println(e);
		} finally {
			System.err.println("bye...");
		}
	}
	
	static void waitKeyToStop() {
		System.err.println("Press a key to stop...");
		try {
			System.in.read();
		} catch (IOException ignored) {
		}
	}
	
	static class Session {
		boolean connected = false;
		String userFirstName = null;
		String userLastName = null;
		Session( String firstName, String lastName ) {
			userFirstName = firstName;
			userLastName = lastName;
		}
	}
	static void session(Socket s, String firstName, String lastName) {
		try ( Scanner in = new Scanner(System.in);
			  ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			  ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream())) {
			Session ses = new Session(firstName, lastName);
			if ( openSession( ses, is, os, in )) { 
				try {
					while (true) {
						Message msg = getCommand(ses, in);
						if (! processCommand(ses, msg, is, os)) {
							break;
						}				
					}			
				} finally {
					closeSession(ses, os);
				}
			}
		} catch ( Exception e) {
			System.err.println(e);
		}
	}
	
	static boolean openSession(Session ses, ObjectInputStream is, ObjectOutputStream os, Scanner in) 
			throws IOException, ClassNotFoundException {
		os.writeObject( new MessageConnect(ses.userFirstName, ses.userLastName));
		MessageConnectResult msg = (MessageConnectResult) is.readObject();
		if (!msg.Error()) {
			System.err.println("connected");
			ses.connected = true;
			return true;
		}
		System.err.println("Unable to connect: " + msg.getErrorMessage());
		System.err.println("Press <Enter> to continue...");
		if( in.hasNextLine())
			in.nextLine();
		return false;
	}
	
	static void closeSession(Session ses, ObjectOutputStream os) throws IOException {
		if ( ses.connected ) {
			ses.connected = false;
			os.writeObject(new MessageDisconnect());
		}
	}

	static Message getCommand(Session ses, Scanner in) {	
		while (true) {
			printPrompt();
			if (!in.hasNextLine())
				break;
			String str = in.nextLine();
			byte cmd = translateCmd(str);
			switch ( cmd ) {
				case -1:
					return null;
				case Protocol.CMD_SHOW_ORDERS:
					return new MessageCheckOrder();
				case Protocol.CMD_USER:
					return new MessageUser();
				case Protocol.CMD_MAKE_ORDER:
					return inputLetter(in);
				case 0:
					continue;
				default: 
					System.err.println("Unknown command!");
					continue;
			}
		}
		return null;
	}
	
	static MessageLetter inputLetter(Scanner in) {
		String usrNic, letter;
		System.out.print("Enter numbers of your choice: ");
		usrNic = in.nextLine();
		System.out.print("Enter home address: ");
		letter = in.nextLine();
		return new MessageLetter(usrNic, letter);
	}
	
	static TreeMap<String,Byte> commands = new TreeMap<String,Byte>();
	static {
		commands.put("q", new Byte((byte) -1));
		commands.put("quit", new Byte((byte) -1));
		commands.put("o", new Byte(Protocol.CMD_SHOW_ORDERS));
		commands.put("orders", new Byte(Protocol.CMD_SHOW_ORDERS));
		commands.put("u", new Byte(Protocol.CMD_USER));
		commands.put("users", new Byte(Protocol.CMD_USER));
		commands.put("m", new Byte(Protocol.CMD_MAKE_ORDER));
		commands.put("make order", new Byte(Protocol.CMD_MAKE_ORDER));
	}
	
	static byte translateCmd(String str) {
		str = str.trim();
		Byte r = commands.get(str);
		return (r == null ? 0 : r);
	}
	
	static void printPrompt() {
		System.out.println();
		System.out.print("(q)uit/(o)rders/(u)sers/(m)ake order >");
		System.out.flush();
	}
	
	static boolean processCommand(Session ses, Message msg, ObjectInputStream is, ObjectOutputStream os)
            throws IOException, ClassNotFoundException {
		if ( msg != null )
		{
			os.writeObject(msg);
			MessageResult res = (MessageResult) is.readObject();
			if ( res.Error()) {
				System.err.println(res.getErrorMessage());
			} else {
				switch (res.getID()) {
					case Protocol.CMD_SHOW_ORDERS:
						printOrders((MessageCheckOrdersResult) res);
						break;
					case Protocol.CMD_USER:
						printUsers(( MessageUserResult ) res);
						break;
					case Protocol.CMD_MAKE_ORDER:
						System.out.println("OK...");
						break;
					default:
						assert(false);
						break;
				}
			}
			return true;
		}
		return false;
	}
	
	static void printOrders(MessageCheckOrdersResult m) {
		if ( m.letters != null && m.letters.length > 0) {
			System.out.println("All orders {");
			for (String str: m.letters) {
				System.out.println(str);
			}
			System.out.println("}");
		}
		else {
			System.out.println("No orders...");
		}
	}
	
	static void printUsers(MessageUserResult m) {
		if ( m.userNics != null ) {
			System.out.println("Users {");
			for (String str: m.userNics) {
				System.out.println("\t" + str);
			}	
			System.out.println("}");
		}
	}
}
