/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De9_cau2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
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
        ArrayList<Sach> listSach = new ArrayList<Sach>();
        //doc flie
        try {
            FileReader fr = new FileReader("sach.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String a[] = s.split("\\$");
                Sach sach = new Sach(a[0], a[1], a[2]);
                listSach.add(sach);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }

        //xuly
        Scanner sc = new Scanner(System.in);
        String messege = "";
        int k = 0;
        while (k != 3) {
            k = Integer.parseInt(sv.nhanDL());
            switch (k) {
                case 1:
                    messege = "";
                    for (Sach listSach1 : listSach) {
                        messege+= listSach1.inThongTin() +"\n";
                    }
                    sv.guiDL(messege);
                    break;
                case 2:
                    int count = 0;
                    messege = "";
                    String s = sv.nhanDL();
                    for (Sach listSach1 : listSach) {
                        if(s.equalsIgnoreCase(listSach1.TenSach)){
                            messege += listSach1.inThongTin()+"\n";
                            count++;
                        }
                    }
                    if(count==0){
                        messege+="sach nay khong co trong thu vien";
                    }  
                    sv.guiDL(messege);
                    int count1 =0;
                    messege = "";
                    String nhan = sv.nhanDL();
                    for (Sach listSach1 : listSach) {
                        if(listSach1.NguoiMuon.equalsIgnoreCase("chua muon")&&listSach1.TenSach.equalsIgnoreCase(s)){
                            listSach1.NguoiMuon = nhan;
                            messege += listSach1.inThongTin();
                            count1++;
                        }
                    }
                    if (count1==0) {
                        messege += "Sach nay da co nguoi muon";
                    }
                    sv.guiDL(messege);
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
