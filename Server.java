import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
 
public class EchoServer {
    public static void main(String[] args) throws IOException {
         args= new String[1];
         args[0]= JOptionPane.showInputDialog("Enter a port for the server");
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
         
        int portNumber = Integer.parseInt(args[0]);
         
        try (
            ServerSocket serverSocket =
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader outo = new BufferedReader(
                new InputStreamReader(System.in)))        
        {
            String inputLine;
            while ((inputLine = outo.readLine()) != null) {
                out.println(inputLine);
                System.out.println("Client: "+in.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
