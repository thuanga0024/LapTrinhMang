/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiNhanVien_TCP_DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kira
 */
public class QLNV_CLient_TCP_DB {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        Socket clientSocket = new Socket("localhost",1999);
        menu();
        int chon;
        System.out.println("Nhập Lựa Chọn Của Bạn");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        chon = Integer.parseInt(inFromUser.readLine());
        ObjectOutputStream outToSever;
        ObjectInputStream inFromServer;
        Message recevieMessage;
        List<NhanVien> listNV;
        switch(chon){
            case Message.XEM_DANH_SACH:
                Message message = new Message();
                message.setChon(Message.XEM_DANH_SACH);
                
                outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                outToSever.writeObject(message);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                recevieMessage = (Message)inFromServer.readObject();
                
                listNV = recevieMessage.getDsNV();
                
                for(NhanVien nv : listNV){
                    System.out.println(nv.toString());
                }
                clientSocket.close();
                break;
            case Message.THEM_NHAN_VIEN:
                NhanVien nv = NhapNhanVienAdd();
                Message messageAdd = new Message();
                messageAdd.setChon(Message.THEM_NHAN_VIEN);
                messageAdd.setNhanvienAdd(nv);
                
                outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                outToSever.writeObject(messageAdd);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                Message messageFinal = (Message)inFromServer.readObject();
                
                System.out.println(messageFinal.getKq());
                clientSocket.close();
                break;
            case Message.TIM_NHAN_VIEN_THEO_TEN:
                Message messageSearch = new Message();
                messageSearch.setChon(Message.TIM_NHAN_VIEN_THEO_TEN);
                System.out.println("Nhập Tên Nhân Viên Bạn Muốn Tìm");
                BufferedReader nameNV = new BufferedReader(new InputStreamReader(System.in));
                messageSearch.setName(nameNV.readLine());
                
                outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                outToSever.writeObject(messageSearch);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                Message recevieSearch = (Message)inFromServer.readObject();
                
                List<NhanVien> listNV1 = recevieSearch.getDsNV();
                
                for(NhanVien sv2 : listNV1){
                    System.out.println(sv2.toString());
                }
                
                clientSocket.close();
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
    
    public static NhanVien NhapNhanVienAdd() throws ParseException{
        NhanVien nv = new NhanVien();
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập Tên Nhân Viên");
        nv.setTenNV(nhap.nextLine());
        System.out.println("Nhập Ngày Sinh");
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String StrNgaySinh = nhap.nextLine().trim();
        nv.setNgaysinh(format.parse(StrNgaySinh));
        System.out.println("Nhập Chức Danh");
        nv.setChucdanh(nhap.nextLine());
        nhap.close();
        return nv;
   }
}
