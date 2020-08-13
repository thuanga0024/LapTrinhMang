package multiply_udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket socket = new DatagramSocket(8888);

        DatagramPacket sendPacket;
        DatagramPacket receivePacket;

        while (true) {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(receiveData);
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            Message mess = (Message) objIn.readObject();
            objIn.close();

            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            int response = multiply(mess.getX(), mess.getY());

            ByteArrayOutputStream byteOut = new ByteArrayOutputStream(1024);
            DataOutputStream dataOut = new DataOutputStream(byteOut);
            dataOut.writeInt(response);

            byte[] sendData = byteOut.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            socket.send(sendPacket);

        }
    }

    static int multiply(int x, int y) {
        return x * y;
    }
}