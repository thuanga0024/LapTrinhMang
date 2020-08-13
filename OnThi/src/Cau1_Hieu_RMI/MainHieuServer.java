/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Hieu_RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Kira
 */
public class MainHieuServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //Tạo Đối Tượng
        HieuImpl hieu = new HieuImpl();
        //Kích Hoạt Quản Lí Đối Tượng Từ Xa
        Registry registry = LocateRegistry.createRegistry(1999);
        //Đăng Kí đối tượng được triệu gọi từ xa vs bộ quản lí
        Naming.rebind("rmi://localhost:1999/Kira",hieu);
        
        System.out.println("Server is Here ....");
        
    }
}
