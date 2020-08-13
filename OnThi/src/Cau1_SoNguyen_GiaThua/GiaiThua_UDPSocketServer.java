/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_SoNguyen_GiaThua;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Kira
 */
public class GiaiThua_UDPSocketServer {
    public static void main(String[] args) throws SocketException, IOException {
        byte[] recevieData = new byte[1024];
        byte[] sendData = new byte[1024];
        String sentence;
        int Number,GiaiThua=1;
        DatagramSocket serverSocket = new DatagramSocket(1999);
        
        System.out.println("Server is waitting ........");
        
        while(true){
            
            DatagramPacket receviePacket = new DatagramPacket(recevieData,recevieData.length);
            
            serverSocket.receive(receviePacket);
            
            sentence = new String(receviePacket.getData());
            Number = Integer.parseInt(sentence.trim());
            
            if(Number==0){
                GiaiThua = 1;
            } else {
                for(int i=1;i<=Number;i++){
                    GiaiThua = GiaiThua*i;
                }
            }
            System.out.println(GiaiThua);
            
            sendData = String.valueOf(GiaiThua).getBytes();
            InetAddress ipAddress = receviePacket.getAddress();
            int port = receviePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,port);
            
            serverSocket.send(sendPacket);
        }
    }
}
