/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_BinhPhuongRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Kira
 */
public class MainBinhPhuongClient {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
        
        IntBinhPhuong Intbp = (IntBinhPhuong)Naming.lookup("rmi://localhost:1999/Kira");
        
        System.out.println("Kết Quả Là:" + Intbp.BinhPhuong(4));
    }
}
