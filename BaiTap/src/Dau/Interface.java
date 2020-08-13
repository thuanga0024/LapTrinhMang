package DauBaiToan;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote{
    public int Cong(int a,int b) throws RemoteException;
    public int Tru(int a,int b) throws RemoteException;
    public int Nhan(int a,int b) throws RemoteException;
    public float Chia(float a,float b) throws RemoteException;
    public int UCLN(int a,int b) throws RemoteException;
}
