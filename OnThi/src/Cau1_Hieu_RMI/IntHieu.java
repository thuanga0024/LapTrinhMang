/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Hieu_RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kira
 */
public interface IntHieu extends Remote{
    public int Hieu(int x, int y) throws RemoteException;
}
