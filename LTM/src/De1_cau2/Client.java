/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De1_cau2;

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
            
            //xu ly
            Scanner sc = new Scanner(System.in);
            String message = "";
            while(true){
                String s = dis.readUTF();
                System.out.println(s);
                System.out.print("Dap an cua ban la: ");
                String dap_an = sc.nextLine();
                dos.writeUTF(dap_an);
                message = dis.readUTF();
                System.out.println(message);
                System.out.println("");
                if(message.equalsIgnoreCase("Tra loi sai")){
                    System.out.println("tro choi ket thuc");
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
