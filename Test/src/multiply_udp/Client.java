package multiply_udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws NumberFormatException, IOException {
        DatagramSocket socket = new DatagramSocket();

        DatagramPacket sendPacket;
        DatagramPacket receivePacket;

        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 8888;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Nhập x: ");
        int x = Integer.parseInt(bf.readLine());
        System.out.print("Nhập y: ");
        int y = Integer.parseInt(bf.readLine());

        Message sendMess = new Message(x, y);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(1024);
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(sendMess);
        objOut.flush();
        objOut.close();

        byte[] sendData = byteOut.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(receiveData);
        DataInputStream dataIn = new DataInputStream(byteIn);
        int res = dataIn.readInt();
        System.out.println("Ket qua: " + res);

        socket.close();
    }
}