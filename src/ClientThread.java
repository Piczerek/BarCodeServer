import java.net.*;
import java.util.logging.Handler;
import java.io.*;

public class ClientThread extends Thread{

	
	public void run()
	{
		try
	      {
			String serverName = "localhost";
			int port = 6066;
			
	         System.out.println("Connecting to " + serverName +
			 " on port " + port);
	         Socket client = new Socket(serverName, port);
	         
	         System.out.println("Just connected to " 
			 + client.getRemoteSocketAddress());
	         
	         OutputStream outToServer = client.getOutputStream();
	         
	         DataOutputStream out = new DataOutputStream(outToServer);
	         
	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
	         java.io.InputStream inFromServer = client.getInputStream();
	         
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
