/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_ThangtoNgay_TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Kira
 */
public class ThangtoNgay_TCPSocketCLient {
    public static void main(String[] args) throws IOException {
        String sentence,Final;
        
        Socket clientSocket = new Socket("localhost",1999);
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nhập vào 1 tháng nào đó:");
        sentence = inFromUser.readLine();
        
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
        
        outToClient.writeBytes(sentence + "\n");
        
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        Final = inFromServer.readLine();
        
        System.out.println("Server Say:" + Final);
        
        clientSocket.close();
    }
}
