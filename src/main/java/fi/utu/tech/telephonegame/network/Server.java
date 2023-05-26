package fi.utu.tech.telephonegame.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	
	private int port;
	public NetworkService olio;
	

	public Server(int serverPort, NetworkService networkService) throws IOException {	
		this.port = serverPort;
		this.olio = networkService;
			
	}	
	@Override
	public void run() {
		while (true) {
			try (ServerSocket ss = new ServerSocket(port)){
					Socket s = ss.accept();				
	                ClientHandler ch = new ClientHandler(s, olio);               
	                Thread myThread = new Thread(ch);
	                myThread.start();
	            }
	         catch (IOException e) {
	}}}}
