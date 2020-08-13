/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau2;

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
            String messege = "";
            int k = 0;
            while (k!=4) {                
                System.out.println("-----------------------MENU----------------------");
                System.out.println("1. Xem danh sach nhan vien");
                System.out.println("2. Them nhan vien moi");
                System.out.println("3. Tim nhan vien theo ten");
                System.out.println("4. Ket thuc");
                System.out.print("Ban chon: ");
                k = Integer.parseInt(sc.nextLine());
                dos.writeInt(k);
                switch(k){
                    case 1:
                        messege = "";
                        messege = dis.readUTF();
                        System.out.println(messege);
                        break;
                    case 2:
                        messege = "";
                        System.out.println("Nhap thong tin nhan vien can them");
                        System.out.print("Ten nhan vien: ");
                        String ten = sc.nextLine();
                        System.out.print("Ngay sinh: ");
                        String ngaysinh = sc.nextLine();
                        System.out.print("Chuc danh: ");
                        String chucdanh = sc.nextLine();
                        messege += ten+"$"+ngaysinh+"$"+chucdanh;
                        dos.writeUTF(messege);
                        break;
                    case 3:
                        messege = "";
                        System.out.print("Moi ban nhap ten nhan vien can tim: ");
                        String tennv = sc.nextLine();
                        dos.writeUTF(tennv);
                        System.out.println("Thong tin nhan vien");
                        messege = dis.readUTF();
                        System.out.println(messege);
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
