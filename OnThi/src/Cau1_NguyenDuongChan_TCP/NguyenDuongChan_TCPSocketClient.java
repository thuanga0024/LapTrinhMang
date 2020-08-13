/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_NguyenDuongChan_TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 *
 * @author Kira
 */
public class NguyenDuongChan_TCPSocketClient {
    public static void main(String[] args) throws IOException {
        
        String sentence,Final;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhập Vào 1 Số Nguyên Dương:");
        sentence = inFromUser.readLine();
        
        //gửi dữ liệu qua server đang chờ kết nối
        //phải có một kết nối đên server đang chờ
        Socket clientSocket = new Socket("localhost",1999);
        
        //Mở Một Cái Luồng OutPut Stream để ghi dữ liệu đến server
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        
        //Dùng Luồng trên để ghi dữ liệu 
        outToServer.writeBytes(sentence+ "\n");
        
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        Final = inFromServer.readLine();
        
        System.out.println("Form Server:" + Final);
        
        clientSocket.close();
    }
}
