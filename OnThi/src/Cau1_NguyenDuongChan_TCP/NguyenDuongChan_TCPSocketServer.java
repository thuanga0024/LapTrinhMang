/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_NguyenDuongChan_TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

/**
 *
 * @author Kira
 */
public class NguyenDuongChan_TCPSocketServer {
    public static void main(String[] args) throws IOException {
        
        ServerSocket welcomeSocket = new ServerSocket(1999);
        String clientSentence,Final;
        System.out.println("Server is waiting ....");
        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            
            //Đọc Dữ Liệu
            //Mở Luồng
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            //Đọc Dữ Liệu
            clientSentence = inFromClient.readLine();
            
            if(Integer.parseInt(clientSentence)%2 == 0){
                Final = "Số Bạn Nhập Là Số Chẵn";
            } else{
                Final = "Số Bạn Nhập Là Số Lẽ";
            }
            
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            outToClient.writeBytes(Final+"\n");
            
            System.out.println("Done");
        }
    }
}
