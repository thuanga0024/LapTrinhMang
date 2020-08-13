/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QLSV_TCP_SQL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
import org.omg.CORBA_2_3.portable.InputStream;

/**
 *
 * @author Kira
 */
public class QSLVCLientTCPDB {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
        menu();
        int chon;
        System.out.println("Hãy Nhập Số Bạn Muốn:");
        chon = Integer.parseInt(inUser.readLine());
        Message recevieMessage;
        ObjectOutputStream outToSever;
        ObjectInputStream inFromServer;
        List<SinhVien> listSV;
        Socket clientSocket = new Socket("localhost",1999);
        switch(chon){
            case Message.XEM_DANH_SACH:
                 Message message = new Message();//Tạo ra 1 tin nhắn mới
                 //Để Truyển qua server
                 message.setChon(Message.XEM_DANH_SACH);
                 
                 outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                 outToSever.writeObject(message);
                 
                 inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                 
                 recevieMessage = (Message)inFromServer.readObject();
                 listSV = recevieMessage.getDsSV();
                 
                 for(SinhVien sv: listSV){
                     System.out.println(sv.toString());
                 }
                break;
            case Message.THEM_SINH_VIEN:
                
                SinhVien sv = nhapSV();
                Message sendMessageAdd = new Message();
                
                sendMessageAdd.setChon(Message.THEM_SINH_VIEN);
                sendMessageAdd.setSinhvienAdd(sv);
                outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                
                outToSever.writeObject(sendMessageAdd);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                
                recevieMessage = (Message)inFromServer.readObject();
                
                System.out.println(recevieMessage.getKq());
                
                break;
            case Message.TIM_SINH_VIEN:
                SinhVien sv1 = NhapNameSearch();
                Message messageSeach = new Message();
                messageSeach.setChon(Message.TIM_SINH_VIEN);
                messageSeach.setSearchName(sv1);
                
                outToSever = new ObjectOutputStream(clientSocket.getOutputStream());
                outToSever.writeObject(messageSeach);
                
                inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                
                recevieMessage = (Message)inFromServer.readObject();
                listSV = recevieMessage.getDsSV();
                 
                 for(SinhVien sv2: listSV){
                     System.out.println(sv2.toString());
                 }
                
                break;
            default:
                clientSocket.close();
                break;
        }
    }
    
    public static void menu(){
        System.out.println("----------- Welcome To Menu ----------- ");
        System.out.println("1. Xem Danh Sách Sinh Viên");
        System.out.println("2. Thêm Nhân Viên Mới");
        System.out.println("3. Tìm Nhân Viên Theo Tên");
        System.out.println("4. Kết Thúc");
        System.out.println("Lựa Chọn 1 Chức Năng Bạn Muốn Chọn");
    }
    
    public static SinhVien nhapSV() throws ParseException{
        SinhVien sv = new SinhVien();
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập Tên SV:");
        sv.setTenSV(nhap.nextLine());
        System.out.println("Nhập Ngày Sinh");
        String strNgaySinh = nhap.nextLine().trim();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        sv.setNgaysinh(format.parse(strNgaySinh));
        System.out.println("Nhập Chức Danh");
        sv.setChucdanh(nhap.nextLine());
        
        nhap.close();
        return sv;
    }
    public static SinhVien NhapNameSearch() throws ParseException{
        SinhVien sv = new SinhVien();
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập Vào Tên Sv Cần Tìm");
        sv.setTenSV(nhap.nextLine());
        String strNgaySinh ="01/01/1999";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        sv.setNgaysinh(format.parse(strNgaySinh));
        sv.setChucdanh("ABC");
        nhap.close();
        return sv;
    }
}
