package uppercase_udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) throws IOException {

        String sentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("HÃY NHẬP VÀO MỘT CÂU: ");

        sentence = inFromUser.readLine();

        DatagramSocket clientSocket = new DatagramSocket();

        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();
        InetAddress ipAddress = InetAddress.getByName("localhost");

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 8888);

        clientSocket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();

    }

}
