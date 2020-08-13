
package SieuThi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2811);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            int k = 0;    //kiem tra yeu cau nguoi dung
            String s = "";      //thong diep gui len Server
            while (k != 3) {
                System.out.println("\n\n-------------MENU--------------");
                System.out.println("1. Tim kiem mat hang theo ma hang");
                System.out.println("2. Lap hoa don thanh toan");
                System.out.println("3. Thoat");
                System.out.print("Ban chon: ");
                k = Integer.parseInt(sc.nextLine());
                dos.writeInt(k);
                switch (k) {
                    case 1:     //Tim kiem mat hang theo ma hang
                        System.out.print("Nhap Ma hang muon tim kiem: ");
                        s = sc.nextLine();
                        dos.writeUTF(s);

                        System.out.println(dis.readUTF());
                        break;
                    case 2:     //Lap hoa don thanh toan
                        int check = 0;      //kiem tra xem nguoi dung chon gi
                        while (check != 2) {
                            System.out.println("\t1. Nhap ID va so luong Hang can mua");
                            System.out.println("\t2. Hien thi Hoa Don");
                            System.out.print("Ban chon: ");
                            check = Integer.parseInt(sc.nextLine());
                            dos.writeInt(check);
                            switch (check) {
                                case 1:
                                    System.out.print("Nhap ID: ");
                                    String ID_canmua = sc.nextLine();
                                    System.out.print("Nhap so luong can mua: ");
                                    int SoLuong_canmua = Integer.parseInt(sc.nextLine());

                                    dos.writeUTF(ID_canmua);
                                    dos.writeInt(SoLuong_canmua);
                                    break;
                                case 2:
                                    int sum = dis.readInt();
                                    String HoaDon = dis.readUTF();
                                    String GhiChu = dis.readUTF();
                                    System.out.println("HOA DON:");
                                    System.out.println(HoaDon);
                                    System.out.println("--> Tong so tien: " + sum);
                                    System.out.println("--> Ghi chu: \n+" + GhiChu);
                                    break;
                                default:
                                    System.out.println("Nhap sai!");
                                    break;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Cam on ban da su dung chuong trinh");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le, de nghi nhap lai!");
                        break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
