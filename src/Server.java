import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Client.Client;

/** @author Alexandre
 * Server class of our project
 * 
 *
 */
public class Server{
	public static void main(String[] args) {

		/**
		 * 1st ArrayList containing the clients
		 * 2nd containing the files
		 * 3rd containing the threads
		 */
		
		ArrayList<Client> clients = new ArrayList<Client>() ;
		ArrayList<String> files = new ArrayList<String>() ;
		ArrayList<AcceptClient> acceptClients = new ArrayList<AcceptClient>() ;
		
		ServerSocket serverSocket ;
		Socket clientSocket ;
		String clientName = null ;
		AcceptClient clientThread ;
		
		
		try {
			
			/**
			 *  AlexandreSchweizer: We initialize the Server Socket
			 */
			
			serverSocket = new ServerSocket(4850) ;
			
			/**
			 *  AlexandreSchweizer: Accept every incoming socket connection
			 */
			
			int i = 1 ;
			while(true) {
				
				/**
				 *  AlexandreSchweizer: We create the client socket object
				 */
				
				clientSocket = serverSocket.accept() ;

				/**
				 *  AlexandreSchweizer: Then we create and start the client thread
				 */
				
				clientThread = new AcceptClient(clientSocket, clients, clientName, i, acceptClients,files) ;
				clientThread.start() ;	 
				
				/**
				 *  AlexandreSchweizer: ClientThread is added to the thread acceptClients 
				 *  in order to constantly have an up-to-date list of clients and files
				 */
				
				i++ ;
				acceptClients.add(clientThread) ;
				
				
		
			}

		}

		catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}







