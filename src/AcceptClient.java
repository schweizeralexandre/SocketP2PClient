import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/** @author Alexandre: This class allows the server to accept clients
 * 
 * @implements Runnable
 * @catch IOException
 *
 */
public class AcceptClient implements Runnable {
	
	private Socket clientSocket ;
	private int idClient ;
	private File[] filesList ;
	private ArrayList<Thread> acceptClient ;
	PrintWriter pout = null ;

	
	/** @author Alexandre: method used to accept the client socket
	 * 
	 * We pass the following parameters: socketClient, idClient, acceptClient
	 */
	
	public AcceptClient(Socket clientSocket, int idClient, ArrayList acceptClient){
		this.clientSocket = clientSocket ;
		this.idClient = idClient ;
		this.acceptClient = acceptClient ;
	}

	public void run() {

			System.out.println("Client " + idClient + " " + clientSocket.getInetAddress().getHostAddress() + " is now connected !") ;
			
			try {
				pout = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream())) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pout.println("Welcome to the server. Please enter your name: ") ;
			pout.flush() ;

	
	}
}



