/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author probx
 */
public class Remote extends UnicastRemoteObject implements Interface{

    public Remote() throws RemoteException {
    }
    
    @Override
    public int binhPhuong(int a) throws RemoteException {
        return a*a;
    }
    
}
