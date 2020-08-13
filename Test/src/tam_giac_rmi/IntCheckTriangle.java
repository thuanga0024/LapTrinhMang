package tam_giac_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntCheckTriangle extends Remote {
    public boolean CheckTriangle(int a, int b, int c) throws RemoteException;
}
