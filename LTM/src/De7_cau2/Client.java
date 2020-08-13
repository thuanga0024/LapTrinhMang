/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De7_cau2;

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
            //xuly
            Scanner sc = new Scanner(System.in);
            int k = 0;
            String message = "";
            while (k != 4) {
                System.out.println("----------------MENU-------------------");
                System.out.println("1. Them khach hang");
                System.out.println("2. Tinh tien phong");
                System.out.println("3. Hien thi thong tin");
                System.out.println("4. Ket thuc");
                System.out.print("Ban chon: ");
                k = Integer.parseInt(sc.nextLine());
                dos.writeUTF(String.valueOf(k));
                switch (k) {
                    case 1:
                        message = "";
                        System.out.println("Moi nhap thong tin khach hang can them");
                        System.out.print("Nhap ma khach hang: ");
                        String makh = sc.nextLine();
                        System.out.print("Nhap ten khach hang: ");
                        String tenkh = sc.nextLine();
                        System.out.print("Nhap loai phong (S/A/B): ");
                        String loaiphong = sc.nextLine();
                        message += makh + "$" + tenkh + "$" + loaiphong;
                        dos.writeUTF(message);
                        break;
                    case 2:
                        int k1 = 0;
                        message = "";
                        while (k1 != 3) {
                            System.out.println("Ban muon chon chuc nang");
                            System.out.println("1. Tim khach hang theo ten");
                            System.out.println("2. Tinh tien phong");
                            System.out.println("3. Thoat chuc nang");
                            System.out.print("ban chon: ");
                            k1 = Integer.parseInt(sc.nextLine());
                            dos.writeInt(k1);
                            switch (k1) {
                                case 1:
                                    System.out.print("Moi ban nhap ten khach hang: ");
                                    String tenkh_tim = sc.nextLine();
                                    dos.writeUTF(tenkh_tim);
                                    message = dis.readUTF();
                                    System.out.println(message);
                                    break;
                                case 2:
                                    message = "";
                                    System.out.print("Moi ban nhap ID khach hang: ");
                                    String ID_new = sc.nextLine();
                                    System.out.print("Moi ban nhap so ngay khach o: ");
                                    int n = Integer.parseInt(sc.nextLine());
                                    String s = ID_new + "$" +n;
                                    dos.writeUTF(s);
                                    message += dis.readUTF();
                                    System.out.println(message);
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
                        message = dis.readUTF();
                        System.out.println("Thong tin khach hang");
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
