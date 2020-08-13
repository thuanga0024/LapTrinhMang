/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_BinhPhuongRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Kira
 */
public class BinhPhuongImpl extends UnicastRemoteObject implements IntBinhPhuong{

    protected BinhPhuongImpl() throws RemoteException{
        
    }
    @Override
    public int BinhPhuong(int x) throws RemoteException {
        System.out.println("Bình Phương .....");
        return x*x;
    }
    
}
