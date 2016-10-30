import java.io.IOException;

public class BarcodeServer {
	
	public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        Thread server = new ServerThread(6066);
        server.start();

    }
	
}
