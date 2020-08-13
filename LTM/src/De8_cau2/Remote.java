/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De8_cau2;

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
    public int Cong(int a, int b) throws RemoteException {
        return a+b;
    }

    @Override
    public int Tru(int a, int b) throws RemoteException {
        return a-b;
    }

    @Override
    public int Nhan(int a, int b) throws RemoteException {
        return a*b;
    }

    @Override
    public float Chia(float a, float b) throws RemoteException {
        return a/b+a%b;
    }

    @Override
    public int UCLN(int a, int b) throws RemoteException {
        while (a!=b) {            
            if(a>b) a = a - b;
            else b = b - a;
        }
        return a;
    }
    
}
