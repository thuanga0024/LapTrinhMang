package convert_num_udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;
        byte[] sendData;
        byte[] receiveData;

        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 8888;
        
        DatagramSocket clientSocket = new DatagramSocket();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Nhập số: ");
        String num = bf.readLine();

        sendData = num.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        clientSocket.send(sendPacket);

        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String result = new String(receivePacket.getData());
        System.out.println("Result from Server: " + result);
    }
}