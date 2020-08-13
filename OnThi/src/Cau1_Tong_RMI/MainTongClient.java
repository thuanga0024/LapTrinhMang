/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Tong_RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Kira
 */
public class MainTongClient {
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IntTong inttong = (IntTong)Naming.lookup("rmi://localhost:1999/Kira");
        System.out.println("Tổng Là:" + inttong.Tong(5, -10));
    }
}
