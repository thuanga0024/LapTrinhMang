/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QLSV_TCP_SQL;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
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
public class QLSVServerTCPDB {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        ServerSocket serverSocket = new ServerSocket(1999);
        Socket serviceSocket ;
        int chon;
        ObjectInputStream inFromClient;
        System.out.println("Server waiting......");
        while(true){
           serviceSocket = serverSocket.accept();
           ObjectOutputStream outToClient;
           inFromClient = new ObjectInputStream(serviceSocket.getInputStream());
           
           Message recevieMessage = (Message)inFromClient.readObject();
           System.out.println("Hello");
           chon = recevieMessage.getChon();
           switch(chon){
               case Message.XEM_DANH_SACH:
                   List<SinhVien> listSV = GetAllSinhVien();
                   
                   outToClient = new ObjectOutputStream(serviceSocket.getOutputStream());
                   recevieMessage.setDsSV(listSV);
                   outToClient.writeObject(recevieMessage);
                   
                   outToClient.flush();
                   break;
               case Message.THEM_SINH_VIEN:
                   int kq;
                   SinhVien sv = recevieMessage.getSinhvienAdd();
                   kq = ThemSinhVien(sv);
                   if(kq == 1){
                       String sendData ="Đã Thêm Thành Công";
                       Message recevieMessageAdd = new Message();
                       recevieMessageAdd.setKq(sendData);
                       outToClient = new ObjectOutputStream(serviceSocket.getOutputStream());
                       outToClient.writeObject(recevieMessageAdd);
                       System.out.println("Done....");
                       outToClient.flush();
                   }else{
                       String sendData ="Thêm Thất Bại";
                       Message recevieMessageAdd = new Message();
                       recevieMessageAdd.setKq(sendData);
                       outToClient = new ObjectOutputStream(serviceSocket.getOutputStream());
                       outToClient.writeObject(recevieMessageAdd);
                       System.out.println("Done....");
                        outToClient.flush();
                   }
                   break;
               case Message.TIM_SINH_VIEN:
                    SinhVien svSearch = recevieMessage.getSearchName();
                    
                    List<SinhVien> listSVSearch = getTenSinhVien(svSearch.getTenSV());
                    
                    Message sendMessage = new Message();
                    
                    sendMessage.setDsSV(listSVSearch);
                    
                    outToClient = new ObjectOutputStream(serviceSocket.getOutputStream());
                    
                    outToClient.writeObject(sendMessage);
                    
                    System.out.println("Done");
                    
                    outToClient.flush();
                   break;
               default:
                   break;
           }
        }
        
    }
    
    //Phương Thức Lấy Lên Danh Sách
    public static List<SinhVien> GetAllSinhVien() throws SQLException{
        ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();
        ResultSet rs;
        Connection conn = getConnection();
        
        String sql ="SELECT * FROM SinhVien";
        
        Statement stmt = conn.createStatement();
        
        rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            SinhVien sinhvien = new SinhVien();
            sinhvien.setTenSV(rs.getString("tenSv"));
            sinhvien.setNgaysinh(rs.getDate("ngaySinh"));
            sinhvien.setChucdanh(rs.getString("chucDanh"));
            
            listSV.add(sinhvien);
        }
        
        conn.close();
        rs.close();
        return listSV;
    }
    public static int ThemSinhVien(SinhVien sv) throws SQLException{
        Connection connection = getConnection();
        String sql="INSERT INTO SinhVien " +
                "(tenSV,ngaySinh,chucDanh) " +
                "VALUES " +
                  "(?,?,?);";
        PreparedStatement preStmt = null;
        preStmt = connection.prepareStatement(sql);
        preStmt.setString(1,sv.getTenSV());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateFormat = formatter.format(sv.getNgaysinh());
        preStmt.setString(2,dateFormat);
        preStmt.setString(3,sv.getChucdanh());
        preStmt.execute();
        connection.close();
        if(preStmt != null){
            return 1;
        }else{
            return 0;
        }
    }
    public static List<SinhVien> getTenSinhVien(String tensv) throws SQLException{
        ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();
        SinhVien sinhvien1 = new SinhVien();
        Connection connection = getConnection();
        ResultSet rs;
        PreparedStatement preStmt = null;
        String value = tensv;
        System.out.println(value);
        String sql="SELECT * FROM SinhVien WHERE tenSV = ?";
        preStmt = connection.prepareStatement(sql);
        preStmt.setString(1,tensv);
        
        rs = preStmt.executeQuery();
        
        while(rs.next()){
            sinhvien1.setTenSV(rs.getString("tenSv"));
            sinhvien1.setNgaysinh(rs.getDate("ngaySinh"));
            sinhvien1.setChucdanh(rs.getString("chucDanh"));
            
            listSV.add(sinhvien1);
        }
        connection.close();
        rs.close();
        return listSV;
    }
    public static Connection getConnection(){
        Connection conn =null;
        String url="jdbc:jtds:sqlserver://localhost:1433/QuanLiSinhVien";
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

