import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	private static String[] nameAndPort = {"10.140.131.234", "6666"};
	
	public static void main(String[] args) throws IOException {
	        
		 args = nameAndPort;
	        if (args.length != 2) {
	            System.err.println(
	                "Usage: java EchoClient <host name> <port number>");
	            System.exit(1);
	        }

	        String hostName = args[0];
	        int portNumber = Integer.parseInt(args[1]);

	        try (
	            Socket echoSocket = new Socket(hostName, portNumber);
	            PrintWriter out =
	                new PrintWriter(echoSocket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(echoSocket.getInputStream()));
	            BufferedReader stdIn =
	                new BufferedReader(
	                    new InputStreamReader(System.in))
	        ) {
	            String userInput;
	            System.out.println("echo: " + in.readLine());
	            while ((userInput = stdIn.readLine()) != null) {
	                out.println(userInput);

	            }
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + hostName);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to " +
	                hostName);
	            System.exit(1);
	        } 
	    }
}
