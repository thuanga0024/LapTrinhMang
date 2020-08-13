package chat_udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    static String USERNAME = "quy";
    static String PASSWORD = "123qwe";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket svSocket = new DatagramSocket(8888);
        DatagramPacket receivePacket;
        DatagramPacket sendPacket;
        byte[] receiveData;
        byte[] sendData;

        Account account;

        // while (true) {
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        svSocket.receive(receivePacket);
        ByteArrayInputStream byteStream = new ByteArrayInputStream(receiveData);
        ObjectInputStream objIn = new ObjectInputStream(byteStream);
        account = (Account) objIn.readObject();
        objIn.close();

        String id = account.getId();
        String password = account.getPassword();

        if (id.equals(USERNAME) && password.equals(PASSWORD)) {
            String response = "Connected";
            sendData = response.getBytes();
            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            svSocket.send(sendPacket);

            ReadMessage readMess = new ReadMessage(svSocket);
            SendMessage sendMess = new SendMessage(svSocket, "Server", ipAddress, port);

            System.out.println("Connected with " + USERNAME);
            readMess.start();
            sendMess.start();
        } else {
            String response = "Error in authenication.";
            sendData = response.getBytes();
            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            svSocket.send(sendPacket);
        }

        // }
    }
}