package tra_cuu_tu_viet_tat_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IntAcronym extends Remote {
    public String shortToFull(String acronym) throws RemoteException, SQLException;

    public String fullToShort(String full) throws RemoteException, SQLException;
}