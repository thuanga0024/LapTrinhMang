package DauBaiToan;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


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
