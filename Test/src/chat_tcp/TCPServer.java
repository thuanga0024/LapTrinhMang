package chat_tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer {

    static String USERNAME = "quy";
    static String PASSWORD = "123qwe";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket svSocket = new ServerSocket(9999);

        while (true) {

            Socket welcomeSocket = svSocket.accept();

            String clientUsername;
            String clientPassword;

            ObjectInputStream objIn = new ObjectInputStream(welcomeSocket.getInputStream());
            Account account = (Account) objIn.readObject();
            clientUsername = account.getUserName();
            clientPassword = account.getPassword();

            if (clientUsername.equals(USERNAME) && clientPassword.equals(PASSWORD)) {
                String response = "Connected";
                DataOutputStream out = new DataOutputStream(welcomeSocket.getOutputStream());
                out.writeBytes(response + "\n");

                ReadMessage readMess = new ReadMessage(welcomeSocket);
                SendMessage sendMess = new SendMessage(welcomeSocket, "Server");

                System.out.println("Connected with " + USERNAME);
                readMess.start();
                sendMess.start();

            } else {
                String response = "Error in authenication.";
                DataOutputStream out = new DataOutputStream(welcomeSocket.getOutputStream());
                out.writeBytes(response + "\n");
            }
        }
    }
}