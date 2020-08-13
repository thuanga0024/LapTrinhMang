package hay_chon_gia_dung_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

	public static void main(String[] args) {
		try {
			HayChonGiaDungimpl haychongiadung = new HayChonGiaDungimpl();
			LocateRegistry.createRegistry(7777);
			try {
				Naming.rebind("rmi://localhost:7777/haychongiadung", haychongiadung);
				System.out.println("Ok");
			} catch (MalformedURLException e) {
				System.out.println("Loi xay ra trong luc rebind");
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			System.out.println("Loi xay ra trong luc tao rmi");
			e.printStackTrace();
		}

	}

}
