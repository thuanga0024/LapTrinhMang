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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Kira
 */
public class DoDai_TCPSocketServer {
    public static void main(String[] args) throws IOException {
        String clientSentence;
        int length;
        ServerSocket welcomeSocket = new ServerSocket(1999);
        
        System.out.println("Server is waiting ...");
        
        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            clientSentence = inFromClient.readLine();
            
            length = clientSentence.length();
            
            DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
            
            outToServer.writeBytes(String.valueOf(length) + "\n");
            
            System.out.println("Done ................");
        }
    }
}
