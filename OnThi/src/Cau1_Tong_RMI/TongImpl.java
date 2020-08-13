/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1_Tong_RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Kira
 */
public class TongImpl extends UnicastRemoteObject implements IntTong{

    protected TongImpl() throws RemoteException{
        
    }
    @Override
    public int Tong(int x, int y) throws RemoteException {
        return x+y;
    }
    
}
