/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_UpString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import sun.security.x509.IPAddressName;

/**
 *
 * @author Kira
 */
public class ChuHoa_UDPSocketClient {
    public static void main(String[] args) throws IOException {
        
        
        String sentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhập Ký Tự Chữ Thường:");
        sentence = inFromUser.readLine();
        
        DatagramSocket clientSocket = new DatagramSocket();
        
        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,1999);
        
        clientSocket.send(sendPacket);
        
        byte[] recevieData = new byte[1024];
        DatagramPacket receviePacket = new DatagramPacket(recevieData,recevieData.length);
        
        clientSocket.receive(receviePacket);
        
        String Final = new String(receviePacket.getData());
        
        System.out.println("From Server:" + Final);
        
        clientSocket.close();
        
    }
}
