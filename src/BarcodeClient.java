import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class BarcodeClient {
	
	public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        try
	      {
			String serverName = "localhost";
			int port = 6066;
			
	         System.out.println("Connecting to " + serverName +
			 " on port " + port);
	         Socket client = new Socket(serverName, port);
	         System.out.println("Just connected to " 
			 + client.getRemoteSocketAddress());
	         OutputStream outToServer = (OutputStream) client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
	         InputStream inFromServer = (InputStream) client.getInputStream();
	         DataInputStream in =
	                        new DataInputStream(inFromServer);
	         System.out.println("Server says " + in.readUTF());
	         client.close();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
    }
}
