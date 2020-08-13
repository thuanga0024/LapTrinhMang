/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiNhanVien_TCP_DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;

/**
 *
 * @author Kira
 */
public class QLNV_CLient_TCP_DB {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost",1999);
        menu();
        int chon;
        System.out.println("Nhập Lựa Chọn Của Bạn");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        chon = Integer.parseInt(inFromUser.readLine());
        
        switch(chon){
            case Message.XEM_DANH_SACH:
                break;
            case Message.THEM_NHAN_VIEN:
                break;
            case Message.TIM_NHAN_VIEN_THEO_TEN:
                break; 
            default:
                clientSocket.close();
                break;
        }
    }
    public static void menu(){
        System.out.println("------------------ Menu Quản Lí Nhân Viên -----------------------");
        System.out.println("1.Xem Danh Sách Nhân Viên");
        System.out.println("2.Thêm Nhân Viên");
        System.out.println("3.Tìm Nhân Viên Theo Tên");
        System.out.println("4.Kết Thúc");
    }
}
