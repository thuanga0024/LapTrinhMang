/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Server {

    public static void main(String[] args) {
        ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
        //doc file
        try {
            FileReader fr = new FileReader("nhanvien.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String a[] = s.split("\\$");
                NhanVien nv = new NhanVien(a[0], a[1], a[2]);
                listNV.add(nv);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }

        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Server da khoi tao");
            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client da ket noi");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                //xu ly
                Scanner sc = new Scanner(System.in);
                String messege = "";
                int k = 0;
                while (k != 4) {
                    k = dis.readInt();
                    switch (k) {
                        case 1:
                            messege = "";
                            for (NhanVien listNV1 : listNV) {
                                messege += listNV1.inThongTin()+"\n";
                            }
                            dos.writeUTF(messege);
                            break;
                        case 2:
                            messege = "";
                            messege = dis.readUTF();
                            String arr[] = messege.split("\\$");
                            NhanVien nv1 = new NhanVien(arr[0], arr[1], arr[2]);
                            listNV.add(nv1);
                            break;
                        case 3:
                            int count = 0;
                            messege = "";
                            String tennv = dis.readUTF();
                            for (NhanVien nv : listNV) {
                                if(tennv.equalsIgnoreCase(nv.TenNV)){
                                    messege += nv.inThongTin()+"\n";
                                    count++;
                                }
                            }
                            if(count == 0){
                                messege += "khong tim thay";
                            }
                            dos.writeUTF(messege);
                            break;
                        case 4:
                            System.out.println("Cam on ban da su dung chuong trinh");
                            break;
                        default:
                            System.out.println("Nhap sai");
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
