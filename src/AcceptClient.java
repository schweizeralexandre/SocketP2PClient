import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.PopupFactory;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import Client.Client;

/** @author Alexandre: This class allows the server to accept clients
 * 
 * @implements Runnable
 * @catch IOException
 *
 */
public class AcceptClient extends Thread {

	private Socket clientSocket ;
	private ArrayList<Client> clients ;
	public ArrayList<String> myfiles= new ArrayList<String>() ;
	public ArrayList<String> files;
	private String clientName ;
	private int idClient ;
	private File[] filesList ;
	private ArrayList<AcceptClient> acceptClient ;
	PrintWriter pout = null ;
	BufferedReader buff ;
	String adressIP;
	/** @author Alexandre: method used to accept the client socket
	 * 
	 * We pass the following parameters: socketClient, clients, clientName, idClient, acceptClient
	 * @param socketClient, clients, clientName, idClient, acceptClient
	 */

	public AcceptClient(Socket clientSocket, ArrayList<Client> clients, String clientName, int idClient, ArrayList<AcceptClient>acceptClient, ArrayList<String> files) {
		this.clientSocket = clientSocket ;
		this.clients = clients ;
		this.clientName = clientName ;
		this.idClient = idClient ;
		this.acceptClient = acceptClient ;
		this.files=files;
	}

	public void run() {

		adressIP = clientSocket.getInetAddress().getHostAddress() ;


		System.out.println("Client " + idClient + " " + adressIP + " is now connected !") ;

		try {
			pout = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream())) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pout.println("Welcome to the server. Please enter your name: ") ;
		pout.flush() ;

		try {
			buff = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())) ;

			clientName = buff.readLine();
			System.out.println(clientName) ;
			getList();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for (Client client : clients) {
//
//			if (client.getNom().equals(clientName)) {
//				pout.println("Welcome back " + clientName) ;
//				pout.flush() ;
//			}
//			else {
//				pout.println("Welcome " + clientName) ;
//				pout.flush() ;
//
//				Client newClient = new Client() ;
//
//				newClient.setNom(clientName) ;
//
//				newClient.setIpAddress(adressIP) ;
//
//				clients.add(newClient) ;
//
//
//	}
//			}

			while(true)
			{
				
				String command="";
				try {
					command = buff.readLine();
				System.out.println("command : "+command);
				if(command!=null)
					switch (Integer.parseInt(command)) 
					{
					case 1:
						sendList();
						break;
					case 2:
						getList();
						break;
					case 3:
						System.out.println("he ask for a file");
						String name=buff.readLine();
						String ip=whohasthefile(name);
						pout.println(ip);
						pout.flush();
						break;
					default:
						break;
					}
				command=null;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
            /*try {
				  ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
				  Object object = objectInput.readObject();
				  ArrayList<String> fichiers = new ArrayList<>();
                  fichiers =  (ArrayList<String>) object;
                  
                  System.out.println("Avant") ;
                  
                  for (String string : fichiers) {
					System.out.println("Fichiers: " + fichiers) ;
					
				}
                  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */

		}
	public String whohasthefile(String file)
	{
		for (AcceptClient thread : acceptClient) {
			for (String string : thread.myfiles) {
				if(string.equals(file))
					
				{
					thread.someonewantsthisfilesoyoucansendhim(file);
					return thread.adressIP;
				}
			}
		}
		return null;
	}
private void someonewantsthisfilesoyoucansendhim(String file) 
{
		// TODO Auto-generated method stub
pout.println("4");
pout.flush();
pout.println(file);
pout.flush();

}

private void getList() {
		// TODO Auto-generated method stub
	
	files.removeAll(myfiles);
	myfiles.removeAll(myfiles);
String name;
try {
	int size=Integer.parseInt(buff.readLine());
	System.out.println(size) ;
	for (int i = 0; i < size; i++) {
	name = buff.readLine();
	System.out.println(name);
	myfiles.add(name);
	}
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
	
files.addAll(myfiles);
	}

//send list to the user
	private void sendList() 
	{
		// TODO Auto-generated method stub
		System.out.println("I ll send the list to the client");
		pout.println(files.size());
		pout.flush();
		
		for (String string : files) 
		{
			pout.println(files);
			pout.flush();
		}
		
	}




	
}



