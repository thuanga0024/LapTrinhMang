/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Hieu_RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Kira
 */
public class HieuImpl extends UnicastRemoteObject implements IntHieu {
    
    protected HieuImpl() throws RemoteException {
    
    }
    
    @Override
    public int Hieu(int x, int y) throws RemoteException {
        return x-y;
    }
    
}
