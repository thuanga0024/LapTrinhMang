/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_DoDaiChuoi_TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Kira
 */
public class DoDai_TCPSocketClient {
    public static void main(String[] args) throws IOException {
        String sentence,Final;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));   
        System.out.println("Nhập Vào 1 Chuỗi Bất Kì:");
        sentence = inFromUser.readLine();
        
        Socket clientSocket = new Socket("localhost",1999);
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        
        outToServer.writeBytes(sentence +"\n");
        
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        Final = inFromServer.readLine();
        
        System.out.println("Server Say: Độ Dài Bạn Vừa Nhập Là:" + Final);
        
        clientSocket.close();
    
    }
    
   
}
