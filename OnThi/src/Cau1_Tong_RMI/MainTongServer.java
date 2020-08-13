/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Tong_RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Kira
 */
public class MainTongServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        
        TongImpl tong = new TongImpl();
        
        Registry registry = LocateRegistry.createRegistry(1999);
        
        Naming.rebind("rmi://localhost:1999/Kira",tong);
        
        System.out.println("Server is running.....");
    }
}
