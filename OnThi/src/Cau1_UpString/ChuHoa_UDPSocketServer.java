/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_UpString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 *
 * @author Kira
 */
public class ChuHoa_UDPSocketServer {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(1999);
        byte[] receviceData = new byte[1024];
        byte[] sendData = new byte[1024];
        String sentence,upToSentence;
        System.out.println("Server is Waiting");
        while(true){
            DatagramPacket receviePacket = new DatagramPacket(receviceData,receviceData.length);
            serverSocket.receive(receviePacket);
            
            sentence = new String(receviePacket.getData());
            
            upToSentence = sentence.toUpperCase();
            
            System.out.println(upToSentence);
            
            sendData = upToSentence.getBytes();
            InetAddress ipAdress = receviePacket.getAddress();
            int port = receviePacket.getPort();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAdress,port);
            
            serverSocket.send(sendPacket);
            
        }
    }
}
