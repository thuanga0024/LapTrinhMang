package hay_chon_gia_dung_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntHayChonGiaDung extends Remote {
	public MatHang getMatHang() throws RemoteException;

	public int checkDuDoan(float giaDuDoan, MatHang matHang) throws RemoteException;
}
