/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De5_cau2;

import java.io.IOException;
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
public class Client {

    DatagramSocket socket;
    DatagramPacket packet;

    public static void main(String[] args) {
        Client cl = new Client();
        cl.connect();
       
        Scanner sc = new Scanner(System.in);
        int k =0;
        String message = "";
        while (k!=5) {            
            System.out.println("-----------------MENU-----------------------");
            System.out.println("1. Thu tien");
            System.out.println("2. Chi tien");
            System.out.println("3. Xem so du");
            System.out.println("4. Xem lich su thu/chi");
            System.out.println("5. Ket thuc");
            System.out.print("Ban chon: ");
            k = Integer.parseInt(sc.nextLine());
            cl.guiDL(String.valueOf(k));
            switch(k){
                case 1:
                    System.out.print("Moi ban nhap so tien thu: ");
                    int tien_thu = Integer.parseInt(sc.nextLine());
                    cl.guiDL(String.valueOf(tien_thu));
                    System.out.println("Thu tien thanh cong, tai khoan +"+tien_thu);
                    break;
                case 2:
                    System.out.print("Moi ban nhap so tien chi: ");
                    int tien_chi = Integer.parseInt(sc.nextLine());
                    cl.guiDL(String.valueOf(tien_chi));
                    System.out.println("Chi tien thanh cong, tai khoan -"+tien_chi);
                    break;
                case 3:
                    int soDu = Integer.parseInt(cl.nhanDL());
                    System.out.println("So du tai khoan la: "+soDu);
                    break;
                case 4:
                    message = cl.nhanDL();
                    System.out.println(message);
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
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guiDL(String s) {
        try {
            byte[] data = new byte[1024];
            data = s.getBytes();
            InetAddress ipServer = InetAddress.getByName("localhost");
            packet = new DatagramPacket(data, data.length, ipServer, 2811);
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
