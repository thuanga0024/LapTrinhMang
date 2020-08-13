/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De6_cau2;

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
            int k = 0;
            String message = "";
            Scanner sc = new Scanner(System.in);
            while (k != 4) {
                System.out.println("-------------------------MENU---------------------");
                System.out.println("1. Hien thi danh sach nha");
                System.out.println("2. Them nha");
                System.out.println("3. Mua nha");
                System.out.println("4. Ket thuc");
                System.out.print("Ban chon: ");
                k = Integer.parseInt(sc.nextLine());
                dos.writeInt(k);
                switch (k) {
                    case 1:
                        System.out.println("Danh sach nha");
                        message = "";
                        message = dis.readUTF();
                        System.out.println(message);
                        break;
                    case 2:
                        message = "";
                        System.out.println("Nhap thong tin nha can them");
                        System.out.print("So nha: ");
                        int sonha = Integer.parseInt(sc.nextLine());
                        System.out.print("Gia ban: ");
                        int giaban = Integer.parseInt(sc.nextLine());
                        System.out.print("Tinh trang: ");
                        String tinhtrang = sc.nextLine();
                        message += sonha+"$"+giaban+"$"+tinhtrang;
                        dos.writeUTF(message);
                        break;
                    case 3:
                        message = "";
                        System.out.print("Nhap so nha can mua: ");
                        int ID = Integer.parseInt(sc.nextLine());
                        dos.writeUTF(String.valueOf(ID));
                        message += dis.readUTF();
                        System.out.println(message);
                        break;
                    case 4:
                        System.out.println("Cam on ban da su dung chuong trinh");
                        break;
                    default:
                        System.out.println("Nhap sai");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
