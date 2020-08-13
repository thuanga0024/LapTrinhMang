package VietTat;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote{
    public String Tat_Sang_Du(String s) throws RemoteException;
    public String Du_Sang_Tat(String s) throws RemoteException;
}
