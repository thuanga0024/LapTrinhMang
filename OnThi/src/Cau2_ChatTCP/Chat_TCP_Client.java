/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_ChatTCP;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Kira
 */
public class Chat_TCP_Client {
    public static void main(String[] args) throws IOException {
        String sentence,serverSentence;
        Boolean check = true;
        System.out.println("Nhập Tin Nhắn Bạn Muốn Gửi");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        while(check == true){
            Socket clientSocket = new Socket("localhost",1999);
            System.out.println("Client Nhập:");
            sentence = inFromUser.readLine();
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(sentence + "\n");
            
            if(sentence.equals("Tam Biet") == false){
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            serverSentence = inFromServer.readLine();
            System.out.println("Server Say: " + serverSentence);
                if(serverSentence.equals("Tam Biet")){
                    System.out.println("Cuộc Trò Chuyện Kết Thúc");
                    clientSocket.close();
                    break;
                }
            } else{
                System.out.println("Cuộc Trò Chuyện Kết Thúc");
                clientSocket.close();
                break;
            }
        }
    }
}
