/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De6_cau2;

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
        ArrayList<Nha> listNha = new ArrayList<Nha>();
        //doc file
        try {
            FileReader fr = new FileReader("nha.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                String a[] = s.split("\\$");
                Nha n = new Nha(Integer.parseInt(a[0]), Integer.parseInt(a[1]), a[2]);
                listNha.add(n);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }

        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Khoi tao server");
            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Client da ket noi");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                //xu ly
                int k = 0;
                String message = "";
                Scanner sc = new Scanner(System.in);
                while (k != 4) {
                    k = dis.readInt();
                    switch (k) {
                        case 1:
                            message = "";
                            for (Nha nha : listNha) {
                                message += nha.inThongTin() + "\n";
                            }
                            dos.writeUTF(message);
                            break;
                        case 2:
                            message = "";
                            message = dis.readUTF();
                            String ar[] = message.split("\\$");
                            Nha n = new Nha(Integer.parseInt(ar[0]), Integer.parseInt(ar[1]), ar[2]);
                            listNha.add(n);
                            break;
                        case 3:
                            boolean kt = true;
                            message = "";
                            int ID = Integer.parseInt(dis.readUTF());
                            for (Nha nha : listNha) {
                                if (nha.TinhTrang.equalsIgnoreCase("da ban")) {
                                    kt = true;
                                } else {
                                    kt = false;
                                }                               
                            }
                            int count = 0;
                            for (Nha nha1 : listNha) {
                                if (ID == nha1.SoNha) {
                                    count++;
                                }
                            }
                            if(kt==false){
                                if(count>0){
                                    kt = true;
                                    message+="Mua nha thanh cong";
                                }else{
                                    message+="Nha khong ton tai";
                                }
                            }else{
                                message+="Nha nay da ban";
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
