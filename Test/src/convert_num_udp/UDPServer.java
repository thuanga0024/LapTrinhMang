package convert_num_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;
        byte[] sendData;
        byte[] receiveData;

        DatagramSocket svSocket = new DatagramSocket(8888);

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            svSocket.receive(receivePacket);

            String receiveNum = new String(receivePacket.getData()).trim();
            int num = Integer.parseInt(receiveNum);
            System.out.println(num);
            String response = convertNumber(num);

            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            sendData = response.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            svSocket.send(sendPacket);
        }
    }

    static String convertNumber(int num) {
        String res = "";

        switch (num) {
            case 0:
                res = "không";
                break;
            case 1:
                res = "một";
                break;
            case 2:
                res = "hai";
                break;
            case 3:
                res = "ba";
                break;
            case 4:
                res = "bốn";
                break;
            case 5:
                res = "năm";
                break;
            case 6:
                res = "sáu";
                break;
            case 7:
                res = "bảy";
                break;
            case 8:
                res = "tám";
                break;
            case 9:
                res = "chín";
                break;
            default:
                break;
        }

        return res;
    }
}