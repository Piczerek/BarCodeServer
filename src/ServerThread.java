import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerThread extends Thread{
	
	private ServerSocket serverSocket;
	private DataOutputStream out; 
	DataInputStream in;
	Socket server;
	
	public ServerThread(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setReuseAddress(true);
	}
	
	public void run()
	{while(true){
		try
        {
       	
           System.out.println("Waiting for client on port " +
           serverSocket.getLocalPort() + "..." + serverSocket.getInetAddress());
           
           server = serverSocket.accept();
           
           System.out.println("Just connected to "
                 + server.getRemoteSocketAddress());
           
           in =
                 new DataInputStream(server.getInputStream());
           
           System.out.println(in.readUTF());
           
           out =
                new DataOutputStream(server.getOutputStream());
           
           out.writeUTF("Thank you for connecting to "
             + server.getLocalSocketAddress() + "\nGoodbye!");
           
        }catch(SocketTimeoutException s)
        {
           System.out.println("Socket timed out!");
        }catch(IOException e)
        {
           e.printStackTrace();

        }
		while(true)
	    {
	         try {
	        	System.out.println("waiting for message");
	        	String msg = in.readUTF(); 
				readMessage(msg);
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
	    }}
		
	}
	
	public String handleResponse(String message)
	{
		if (message.equals("8712100379765"))
		{
			return "Zupka chinska";
		}
		else if(message.equals("5902930000387"))
		{
			return "tania woda z biedry";
		}
		return "brak produktu w bazie";
	}
	
	public void readMessage(String message) throws IOException
	{
		System.out.println(message);
		out.writeUTF(handleResponse(message));
	}
}
