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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Kira
 */
public class ThangtoNgay_TCPSocketServer {
    public static void main(String[] args) throws IOException {
        String clientSentence,Final = null;

        ServerSocket welcomeServer = new ServerSocket(1999);
        System.out.println("Server is waiting");
        
        while(true){
            Socket connectionSocket = welcomeServer.accept();
            
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromUser.readLine();
            switch(Integer.parseInt(clientSentence)){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:{
                  Final ="Tháng Bạn Nhập Có 31 Ngày";
                  break;
                }
                case 2:{
                  Final ="Tháng Bạn Nhập Có 28 Ngày Hoặc 29 Ngày";
                  break;
                }
                case 4:
                case 6:
                case 9:
                case 11:{
                  Final ="Tháng Bạn Nhập Có 30 Ngày";
                  break;
                }
                default:{
                  Final ="Tháng Bạn Nhập Không Đúng";
                  break;
                }
              
            }
            
            DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
            outToServer.writeBytes(Final + "\n");
            System.out.println("Done .......");
        }
    
    }
    
    
}
