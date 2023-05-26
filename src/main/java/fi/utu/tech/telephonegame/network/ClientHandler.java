package fi.utu.tech.telephonegame.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientHandler implements Runnable{
	public Socket s;
	public ObjectOutputStream os;
	public ObjectInputStream is;
	public NetworkService olio;
	public static List<ClientHandler> Socketit = Collections.synchronizedList(new ArrayList<>());

	public ClientHandler(Socket s, NetworkService olio) throws IOException {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.olio = olio;
			
	}

    public ClientHandler(String peerIP, int peerPort, NetworkService olio) throws UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
    	this.s = new Socket(peerIP, peerPort);
    	this.olio = olio;
    	
    }

	public void send(Serializable out) throws IOException {
				os = new ObjectOutputStream(s.getOutputStream());
				os.writeObject(out);		
	}
	
	@Override
    public void run() {
		Socketit.add(this);
		while (true) {
			Serializable in;
			try {
				is = new ObjectInputStream(s.getInputStream());
				in = (Serializable) is.readObject();
				olio.lisaaViesti(in); 
				System.out.println(in);
				
					
			} catch (ClassNotFoundException e) {

			} catch (IOException e) {

			}						
	}}}