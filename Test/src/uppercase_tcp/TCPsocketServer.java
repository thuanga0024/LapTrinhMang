package uppercase_tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPsocketServer {

    public static void main(String[] args) throws IOException {
        String clientSentence;
        String upSentence;
        
        ServerSocket welcomeSocket = new ServerSocket(9999);

        System.out.println("Hello, welcome to Server, Waiting ...");
        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            clientSentence = inFromClient.readLine();

            upSentence = clientSentence.toUpperCase() + "\n";

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            outToClient.writeBytes(upSentence);

            System.out.println("Done !!!");

        }

    }

}