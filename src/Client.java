import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Client implements Serializable {

	private String Nom;
	private String IpAddress;
	private String UserId;
	private static ArrayList<String> fichiers=new ArrayList<String>();
	private File file;
	private String IPServer = "192.168.56.1";
	public static Socket socket=null;
	private JFileChooser Directory = new JFileChooser();

	
	// Constructor client
	
	
	public void Clientest(String nom, String prenom, String ipAddress, String userId, ArrayList<String> fichers) {

		
		//super();
		Nom = nom;
		IpAddress = ipAddress;
		UserId = userId;
		this.fichiers = fichiers;
	}
	
	
	
	
	
	
	
	
	
	public Client() {
		
		
		JFileChooser chooser = new JFileChooser();
	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	 
	    File file = new File(chooser.getSelectedFile(),"");
	    listFilesForFolder(file);
		
		

	}


	
	public String getIpAddressByName(String Name) {
		
		if(this.Nom.equals(Name)){
			
		try {
			this.IpAddress = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		
		
		
		return this.IpAddress;
		
		}
		
		else return "client n'existe pas";
	}


	// méthode qui permet de récupérer les fichiers

	 public void listFilesForFolder(File folder) 
		{
		
		 File file = new File(folder.getAbsolutePath());
	     File[] files = file.listFiles();
	   
	    	 for (int i = 0; i < files.length; i++) 
	    	    {
	    		  try 
	    		     {
	    	         if( files[i].isFile())
	    	         {
	    	        	 fichiers.add(files[i].getName());
	    	        	
	    	         }
	    	         else
	    	         {
	    	        	 listFilesForFolder(files[i]);
	    	         }
	    		     } catch (Exception e) {
	    		 		
	    		 	}
	    	     }
		
	    
		 
		}





	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

	public String getIpAddress() {
		return IpAddress;
	}
	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}

	public ArrayList<String> getFichers() {
		return fichiers;
	}


	public void setFichers(ArrayList<String> fichers) {
		this.fichiers = fichers;
	}

	
	

	
	
}
