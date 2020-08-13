/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De5_cau2;

import java.io.IOException;
import static java.lang.Math.abs;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Server {

    DatagramSocket socket;
    DatagramPacket packet;

    public static void main(String[] args) {
        Server sv = new Server();
        sv.connect();

        Scanner sc = new Scanner(System.in);
        int taiKhoan = 0;
        int k = 0;
        String message = "Lich su thu/chi:\n";
        while (k != 5) {
            k = Integer.parseInt(sv.nhanDL());
            switch (k) {
                case 1:
                    int tien_thu = Integer.parseInt(sv.nhanDL());
                    taiKhoan += tien_thu;
                    message += "--> Thu: " + tien_thu +"\n";
                    break;
                case 2:
                    int tien_chi = Integer.parseInt(sv.nhanDL());
                    taiKhoan -= tien_chi;
                    message += "--> Chi: " + tien_chi +"\n";
                    break;
                case 3:
                    sv.guiDL(String.valueOf(taiKhoan));
                    break;
                case 4:
                    sv.guiDL(message);
                    break;
                case 5:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                default:
                    System.out.println("Nhap sai");
            }
        }
    }

    public void connect() {
        try {
            socket = new DatagramSocket(2811);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guiDL(String s) {
        try {
            byte[] data = new byte[1024];
            data = s.getBytes();
            InetAddress ipClient = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(data, data.length, ipClient, port);
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String nhanDL() {
        try {
            byte[] data = new byte[1024];
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String s = new String(packet.getData()).trim();
            return s;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
