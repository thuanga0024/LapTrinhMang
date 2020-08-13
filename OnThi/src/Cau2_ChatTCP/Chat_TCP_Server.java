/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_ChatTCP;

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
public class Chat_TCP_Server {
    public static void main(String[] args) throws IOException {
        String clientSentence,sentence;
        ServerSocket welcomeServer = new ServerSocket(1999);
        System.out.println("Server Running ...");
        while(true){
            Socket connection = welcomeServer.accept();
            
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            clientSentence = inFromClient.readLine();
            
            System.out.println("Client Say:" + clientSentence);
            
            if(clientSentence.equals("Tam Biet") == false){
            
            System.out.println("Server Nhập:");
            
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
            
            sentence = inFromServer.readLine();
            
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
            
            outToClient.writeBytes(sentence + "\n");
                if(sentence.equals("Tam Biet")){
                    System.out.println("Cuộc Trò Chuyện Kết Thúc");
                    connection.close();
                    break;
                }
            } else{
                System.out.println("Cuộc Trò Chuyện Kết Thúc");
                connection.close();
                break;
            }
        }
    }
}
