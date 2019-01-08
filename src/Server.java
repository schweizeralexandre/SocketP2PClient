

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Client.Client;

public class Server{
	public static void main(String[] args) {

		ArrayList<Client> clients = new ArrayList<Client>() ;
		ArrayList<String>FichiersDisponibles = new ArrayList<String>();



		ServerSocket socket;
		try {
			socket = new ServerSocket(2501);
			Thread t = new Thread(new Accepterclients(socket));
			t.start();
			//System.out.println("Mes employeurs sont prêts !");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

class Accepterclients implements Runnable {

	private ServerSocket socketserver;
	private Socket socket;
	private int nbrclient = 1;
	public Accepterclients(ServerSocket s){
		socketserver = s;
	}

	public void run() {

		try {
			while(true){

				socket = socketserver.accept(); // Un client se connecte on l'accept
				System.out.println("Le client numéro "+nbrclient+ " est connecté !");
				nbrclient++;
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}





