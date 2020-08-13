package SoSanhHang;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IMyRemote extends Remote {

    public String randomMatHang() throws RemoteException;

    public String kiemTraDuDoan(String TenMH, int n) throws RemoteException;
}
