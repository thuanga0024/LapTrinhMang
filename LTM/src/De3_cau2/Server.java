/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De3_cau2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Da khoi tao server");
            while (true) {                
                Socket socket = myServer.accept();
                System.out.println("Client da ket noi");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                Scanner sc = new Scanner(System.in);
                String message = "";
                System.out.println("--------------CHAT-----------------");
                while (true) {                    
                    message = dis.readUTF();
                    System.out.println("Client: "+message);
                    if(message.equalsIgnoreCase("tam biet")){
                        System.out.println("----------------------------------");
                        System.out.println("Ket thuc");
                        break;
                    }
                    message = "";
                    System.out.print("Server: ");
                    message = sc.nextLine();
                    dos.writeUTF(message);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
