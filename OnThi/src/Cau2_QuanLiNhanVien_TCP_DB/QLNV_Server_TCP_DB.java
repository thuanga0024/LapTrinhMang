/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiNhanVien_TCP_DB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kira
 */
public class QLNV_Server_TCP_DB {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket serverSocket = new ServerSocket(1999);
        ObjectOutputStream outToClient;
        ObjectInputStream inFromClient;
        Socket recevieSocket;
        int chon;
        System.out.println("Server is waiting.....");
        while(true){
            recevieSocket = serverSocket.accept();
            
            inFromClient = new ObjectInputStream(recevieSocket.getInputStream());
            
            Message recevieMessage = (Message)inFromClient.readObject();
            
            chon = recevieMessage.getChon();
            switch(chon){
                case Message.XEM_DANH_SACH:
                List<NhanVien> listNV = XemDanhSach();
                recevieMessage.setDsNV(listNV);
                
                outToClient = new ObjectOutputStream(recevieSocket.getOutputStream());
                outToClient.writeObject(recevieMessage);
                outToClient.flush();
                System.out.println("Done ........");
                
                break;
            case Message.THEM_NHAN_VIEN:
                int kq;
                NhanVien nv = recevieMessage.getNhanvienAdd();
                kq = ThemNhanVien(nv);
                if(kq==1){
                    String sendKq = "Đã Thêm Thành Công";
                    Message sendMessage = new Message();
                    sendMessage.setKq(sendKq);
                    outToClient = new ObjectOutputStream(recevieSocket.getOutputStream());
                    outToClient.writeObject(sendMessage);
                    outToClient.flush();
                    System.out.println("Done.............");
                }else{
                    String sendKq = "Thêm Không Thành Công";
                    Message sendMessage = new Message();
                    sendMessage.setKq(sendKq);
                    outToClient = new ObjectOutputStream(recevieSocket.getOutputStream());
                    outToClient.writeObject(sendMessage);
                    outToClient.flush();
                    System.out.println("Done.............");
                }
                break;
            case Message.TIM_NHAN_VIEN_THEO_TEN:
                String name = recevieMessage.getName();
                List<NhanVien> listNV1 = SearchNameNV(name);
                Message SendMessageSearch = new Message();
                SendMessageSearch.setDsNV(listNV1);
                
                outToClient = new ObjectOutputStream(recevieSocket.getOutputStream());
                outToClient.writeObject(SendMessageSearch);
                outToClient.flush();
                System.out.println("Done Câu 3");
                
                break; 
            default:
                break;
            }
        }
    }
    public static List<NhanVien> XemDanhSach() throws SQLException{
        ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
        Connection conn = getConnection();
        ResultSet rs;
        String sql ="SELECT * " +
                    "FROM NhanVien";
        Statement stmt = conn.createStatement();
        
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            NhanVien nv = new NhanVien();
            nv.setTenNV(rs.getString("tenNv"));
            nv.setNgaysinh(rs.getDate("ngaysinh"));
            nv.setChucdanh(rs.getString("chucdanh"));
            
            listNV.add(nv);
        }
        conn.close();
        rs.close();
        return listNV;
    } 
    public static int ThemNhanVien(NhanVien nv) throws SQLException{
        int kq=0;
        Connection connn = getConnection();
        PreparedStatement premnt = null;
        String sql ="INSERT INTO NhanVien " +
                    "(tenNV,ngaysinh,chucdanh) " +
                    "VALUES " +
                    "(?,?,?)";
        premnt = connn.prepareStatement(sql);
        premnt.setString(1,nv.getTenNV());
        SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
        String DateFormat = formater.format(nv.getNgaysinh());
        premnt.setString(2,DateFormat);
        premnt.setString(3,nv.getChucdanh());
        premnt.execute();
        premnt.close();
        connn.close();
        if(premnt !=null){
            kq = 1;
        }
        else{
            kq=0;
        }
        return kq;
    }
    public static List<NhanVien> SearchNameNV(String name) throws SQLException{
        ArrayList<NhanVien> listNv = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement presmnt = null;
        ResultSet rs;
        String sql ="SELECT * FROM NhanVien WHERE tenNV = ?";
        presmnt = conn.prepareStatement(sql);
        presmnt.setString(1,name);
        rs = presmnt.executeQuery();
        while(rs.next()){
            NhanVien nv = new NhanVien();
            nv.setTenNV(rs.getString("tenNV"));
            nv.setNgaysinh(rs.getDate("ngaysinh"));
            nv.setChucdanh(rs.getString("chucdanh"));
            
            listNv.add(nv);
        }
        
        
        return listNv;
    }
    public static Connection getConnection(){
        Connection conn =null;
        String url="jdbc:jtds:sqlserver://localhost:1433/QuanLiNhanVien";
        String user="sa";
        String password ="P@ssWord123";
        
        try {
            conn = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    } 
    
}
