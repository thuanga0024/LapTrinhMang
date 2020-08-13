/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De3_cau2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2811);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            String message = "";
            System.out.println("------------CHAT------------");
            while (true) {
                System.out.print("Client: ");
                message = sc.nextLine();
                dos.writeUTF(message);

                message = "";
                message = dis.readUTF();
                System.out.println("Server: " + message);
                if (message.equalsIgnoreCase("tam biet")) {
                    System.out.println("----------------------------------");
                    System.out.println("Ket thuc");
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
