package tam_giac_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CheckTriangleImpl extends UnicastRemoteObject implements IntCheckTriangle {

    private static final long serialVersionUID = 1L;

    protected CheckTriangleImpl() throws RemoteException {
    }

    @Override
    public boolean CheckTriangle(int a, int b, int c) throws RemoteException {
        return a + b >= c || a + c >= b || b + c >= a;
    }

}
