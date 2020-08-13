/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_KyTu0to9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Kira
 */
public class KyTu_UDPSocketServer {
    public static void main(String[] args) throws SocketException, IOException {
        //Công cụ để nhận
        String sendClient;
        DatagramSocket serverSocket = new DatagramSocket(1999);
        byte[] recevieData = new byte[1024];// Mảnh byte dùng để nhận DL
        
        System.out.println("Server is waiting ........");
        while(true){
            
            //Dữ liệu nhận để đâu
            DatagramPacket receviePacket = new DatagramPacket(recevieData,recevieData.length);
            
            //Thực Hiện Nhận Dữ Liệu
            serverSocket.receive(receviePacket);
            
            //Lấy ra câu/chuỗi
            String sentence = new String(receviePacket.getData());
            int xuli = Integer.parseInt(sentence.trim());
            System.out.println(xuli);
            //Xử Lí Chuyển số sang chữ
            switch(xuli){
                case 1:
                    System.out.println("Một");
                    sendClient="Một";
                    break;
                case 2:
                    System.out.println("Hai");
                    sendClient="Hai";
                    break;
                case 3:
                    System.out.println("Ba");
                    sendClient="Ba";
                    break;
                case 4:
                    System.out.println("Bốn");
                    sendClient="Bốn";
                    break;
                case 5:
                    System.out.println("Năm");
                    sendClient="Năm";
                    break;
                case 6:
                    System.out.println("Sáu");
                    sendClient="Sáu";
                    break;
                case 7:
                    System.out.println("Bảy");
                    sendClient="Bảy";
                    break;
                case 8:
                    System.out.println("Tám");
                    sendClient="Tám";
                    break;
                case 9:
                    System.out.println("Chín");
                    sendClient="Chín";
                    break;
                default:
                    sendClient="Sorry Số Bạn Nhập Vượt quá 9 tôi không thể xử lí được";
                    break;
            }
            
            //Gửi Lại Client
            //Phải gói DL để gửi đi
            byte[] sendData = new byte[1024];
            sendData = sendClient.getBytes();
            int port = receviePacket.getPort();
            InetAddress ipAddress = receviePacket.getAddress();
            //Gói DL sẵn sàng gửi lại 
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddress,port);
            
            //Thực Hiện Việc Gửi DL Đi
            serverSocket.send(sendPacket);
            
        }
    }
}
