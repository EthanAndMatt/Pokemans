import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;


public class Client extends JFrame {
	
	GraphicPanel fr;
	
	public Client(String string) {
		super(string);
	}

	public static void main(String[] args){
		Client c = new Client("Client Frame");
		c.setUpUI();
		c.pack();
		c.setVisible(true);
	}

	private void setUpUI() {
		fr = new GraphicPanel(this);
		fr.initializeIPP();
		this.add(fr);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	public void connect(String ip, String port){
		String[] nameAndPort = {ip, port};
		if (nameAndPort.length != 2) {
			fr.Error_usage();
			fr.print("Usage: <host name> <port number>");
		}

		String hostName = nameAndPort[0];
		int portNumber = Integer.parseInt(nameAndPort[1]);

		try (
				Socket s = new Socket(hostName, portNumber);
				PrintWriter out =
						new PrintWriter(s.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(s.getInputStream()));
				BufferedReader stdIn =
						new BufferedReader(
								new InputStreamReader(System.in))
				) {
			String userInput;
//			System.out.println("echo: " + in.readLine());
			fr.print(in.readLine());
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				
			}
		} catch (UnknownHostException e) {
			fr.print("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
			System.exit(1);
		} 
		
	}
}
