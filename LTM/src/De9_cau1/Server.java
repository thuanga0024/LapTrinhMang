/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De9_cau1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
                int kq = 0;
                int n = dis.readInt();
                switch (n) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        kq += 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        kq += 30;
                        break;
                    case 2:
                        kq += 28;
                        break;
                }
                dos.writeInt(kq);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
