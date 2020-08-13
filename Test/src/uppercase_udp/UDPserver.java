package uppercase_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver {

    public static void main(String[] args) throws IOException {

        // CÔNG CỤ ĐỂ NHẬN/GỬI LÀ:
        DatagramSocket serverSocket = new DatagramSocket(8888);

        byte[] receiveData = new byte[1024];

        byte[] sendData = new byte[1024];

        System.out.println("Waitting ...");

        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());

            String upSentence = sentence.toUpperCase();

            sendData = upSentence.getBytes();

            int port = receivePacket.getPort();

            InetAddress ipAddress = receivePacket.getAddress();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);

            serverSocket.send(sendPacket);

        }

    }

}