package chat_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket clientSocket = new Socket("localhost", 9999);

        String userName;
        String password;
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Nhap ten dang nhap: ");
        userName = inFromClient.readLine();
        System.out.print("Nhap mat khau: ");
        password = inFromClient.readLine();

        Account account = new Account(userName, password);
        ObjectOutputStream objOut = new ObjectOutputStream(clientSocket.getOutputStream());
        objOut.writeObject(account);
        objOut.flush();

        String response = inFromServer.readLine();

        if (response.equals("Connected")) {
            SendMessage sendMess = new SendMessage(clientSocket, userName);
            ReadMessage readMess = new ReadMessage(clientSocket);

            sendMess.start();
            readMess.start();
        } else {
            System.out.println(response);
        }

    }
}