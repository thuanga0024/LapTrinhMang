package chat_udp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket receivePacket;
        DatagramPacket sendPacket;
        byte[] receiveData;
        byte[] sendData;

        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 8888;

        String id;
        String password;
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nhap ten dang nhap: ");
        id = inFromClient.readLine();
        System.out.print("Nhap mat khau: ");
        password = inFromClient.readLine();

        Account account = new Account(id, password);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
        ObjectOutputStream objOut = new ObjectOutputStream(byteStream);
        objOut.writeObject(account);
        objOut.flush();
        objOut.close();

        sendData = byteStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        clientSocket.send(sendPacket);

        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData()).trim();

        if (response.equals(new String("Connected"))) {
            SendMessage sendMess = new SendMessage(clientSocket, id, ipAddress, port);
            ReadMessage readMess = new ReadMessage(clientSocket);

            sendMess.start();
            readMess.start();
        }

        else {
            System.out.println(response);
        }

    }
}