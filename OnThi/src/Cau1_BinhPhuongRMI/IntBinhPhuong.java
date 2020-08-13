/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_BinhPhuongRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kira
 */
public interface IntBinhPhuong extends Remote {
    
    public int BinhPhuong(int x) throws RemoteException;
}
