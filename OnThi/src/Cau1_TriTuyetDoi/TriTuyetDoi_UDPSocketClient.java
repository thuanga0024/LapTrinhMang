/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_TriTuyetDoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Kira
 */
public class TriTuyetDoi_UDPSocketClient {
    public static void main(String[] args) throws SocketException, IOException {
        byte[] sendData = new byte[1024];
        byte[] recevieData = new byte[1024];
        String sentence,Final;
        DatagramSocket clientSocket = new DatagramSocket();
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhập Vào 1 Số Bất Kỳ:");
        sentence = inFromUser.readLine();
        
        sendData = sentence.getBytes();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,1999);
        
        clientSocket.send(sendPacket);
        
        DatagramPacket receivePacket = new DatagramPacket(recevieData,recevieData.length);
        
        clientSocket.receive(receivePacket);
        
        Final = new String(receivePacket.getData());
        
        System.out.println("From User Say: Giá Trị Tuyệt Đối:" + Final);
        
        clientSocket.close();
    }
}
