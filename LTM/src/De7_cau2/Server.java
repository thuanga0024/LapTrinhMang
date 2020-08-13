/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De7_cau2;

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
        ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
        //doc file
        try {
            FileReader fr = new FileReader("khachhang.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String a[] = s.split("\\$");
                KhachHang kh = new KhachHang(a[0], a[1], a[2]);
                listKH.add(kh);
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
                //xuly
                Scanner sc = new Scanner(System.in);
                int k = 0;
                String message = "";
                while (k != 4) {
                    k = Integer.parseInt(dis.readUTF());
                    switch (k) {
                        case 1:
                            message = dis.readUTF();
                            String ar[] = message.split("\\$");
                            KhachHang kh_new = new KhachHang(ar[0], ar[1], ar[2]);
                            listKH.add(kh_new);
                            break;
                        case 2:
                            message = "";
                            int k1 = 0;
                            while (k1 != 3) {
                                k1 = dis.readInt();
                                switch (k1) {
                                    case 1:
                                        message = "";
                                        int count = 0;
                                        String tenkh_tim = dis.readUTF();
                                        for (KhachHang kh1 : listKH) {
                                            if(tenkh_tim.equalsIgnoreCase(kh1.Ten_KH)){
                                                message+= kh1.inThongTin()+"\n";
                                                count++;
                                            }
                                        }
                                        if(count == 0){
                                            message += "Khong tim thay khach hang";
                                        }
                                        dos.writeUTF(message);
                                        break;
                                    case 2:
                                        int count1=0;
                                        message = "";
                                        String s1 = dis.readUTF();
                                        String s2[] = s1.split("\\$");
                                        //s2[0] ID
                                        //s2[1] so ngay
                                        int n = Integer.parseInt(s2[1]);
                                        for (KhachHang kh1 : listKH) {
                                            if(s2[0].equals(kh1.MaKH)){
                                                message += kh1.inThongTin()+"\t|\t"
                                                        +"Tong tien phong: "+kh1.tinhTienPhong(n);
                                                count1++;
                                            }
                                        }
                                        if(count1==0){
                                            message += "Khach hang khong ton tai";
                                        }
                                        dos.writeUTF(message);
                                        break;
                                    case 3:
                                        System.out.println("Thoat chuc nang");
                                        break;
                                    default:
                                        System.out.println("Nhap sai");
                                }
                            }
                            break;
                        case 3:
                            message = "";
                            for (KhachHang kh : listKH) {
                                message += kh.inThongTin() + "\n";
                            }
                            dos.writeUTF(message);
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
