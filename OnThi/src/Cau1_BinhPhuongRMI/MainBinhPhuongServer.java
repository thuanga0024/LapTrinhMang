/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_BinhPhuongRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Kira
 */
public class MainBinhPhuongServer  {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        BinhPhuongImpl bp = new BinhPhuongImpl();
        
        Registry registry = LocateRegistry.createRegistry(1999);
        
        Naming.rebind("rmi://localhost:1999/Kira",bp);
        
        System.out.println("Server Running....");
    }
   
    
}
