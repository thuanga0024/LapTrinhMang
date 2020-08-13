/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_TriTuyetDoi;

import java.io.IOException;
import static java.lang.Math.abs;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Kira
 */
public class TriTuyetDoi_UDPSocketServer {
    public static void main(String[] args) throws SocketException, IOException {
        byte[] recevieData = new byte[1024];
        byte[] sendData = new byte[1024];
        String sentence;
        int a;
        DatagramSocket serverSocket = new DatagramSocket(1999);
        
        System.out.println("Server Đứng Đợi ......");
        while(true){
            DatagramPacket receviePacket = new DatagramPacket(recevieData,recevieData.length);
            
            serverSocket.receive(receviePacket);
            
            sentence = new String(receviePacket.getData());
            
            a = Integer.parseInt(sentence.trim());
            
            
            if(a<0){
                a = Math.abs(a);
            }
            System.out.println(a);
            String temp = String.valueOf(a);
            sendData = temp.getBytes();
            InetAddress ipAddress = receviePacket.getAddress();
            int port = receviePacket.getPort();
            System.out.println(sendData.length);
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,port);
            
            serverSocket.send(sendPacket);
            
            
        }
    }
}
