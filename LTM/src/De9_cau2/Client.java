/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De9_cau2;

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

        //xu ly
        Scanner sc = new Scanner(System.in);
        String messege = "";
        int k = 0;
        while (k != 3) {
            System.out.println("---------------------MENU--------------------");
            System.out.println("1. Hien thi danh sach sach");
            System.out.println("2. Muon sach");
            System.out.println("3. Ket thuc");
            System.out.print("Ban chon: ");
            k = Integer.parseInt(sc.nextLine());
            cl.guiDL(String.valueOf(k));
            switch (k) {
                case 1:
                    messege = cl.nhanDL();
                    System.out.println("Thong tin sach");
                    System.out.println(messege);
                    break;
                case 2:
                    messege = "";
                    System.out.print("Moi ban nhap ten sach can tim: ");
                    String ten = sc.nextLine();
                    cl.guiDL(ten);
                    messege = cl.nhanDL();
                    System.out.println(messege);
                    if(!messege.equalsIgnoreCase("sach nay khong co trong thu vien")){
                        System.out.print("Moi ban nhap ten nguoi muon: ");
                        String ten_muon = sc.nextLine();
                        cl.guiDL(ten_muon);
                    }
                    String nhanDL = cl.nhanDL();
                    System.out.println(nhanDL);
                    
                    break;
                case 3:
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
