/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_KyTu0to9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 *
 * @author Kira
 */
public class KyTu_UDPSocketClient {
    
    public static void main(String[] args) throws IOException {
        
        String sentence,Final;
        //Công Cụ Nhập
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhập Vào Một Chữ Số:");
        sentence = inFromUser.readLine();
        
        // Gửi DL qua người nhận
        // Công cụ để gửi
        DatagramSocket clientSocket = new DatagramSocket();// TCP: Xác Định Port,IP
        
        //Gói Dữ Liệu
        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();
        InetAddress ipAddress = InetAddress.getByName("localhost");//Lấy Địa Chỉ IP của máy server
        
        //Đây Là gói dữ liệu để gửi đi qua người nhận
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,1999);
        
        //Thực Hiện việc gửi gói Dữ Liệu
        clientSocket.send(sendPacket);
        
        //Nhận DL đã được xử lí
        byte[] recevieData = new byte[1024];
        
        DatagramPacket receviePacket = new DatagramPacket(recevieData,recevieData.length);
        clientSocket.receive(receviePacket);
        Final = new String(receviePacket.getData());
        
        System.out.println("From Server: "+ Final);
        
        clientSocket.close();
    }
    
}
