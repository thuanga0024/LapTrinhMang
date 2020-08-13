package tra_cuu_tu_viet_tat_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    private static int port = 9999;

    public static void main(String[] args) {
        try {
            AcronymImpl acro = new AcronymImpl();
            LocateRegistry.createRegistry(port);

            try {
                Naming.rebind("rmi://localhost:9999/getAcronym", acro);
                System.out.println("Ok");
            } catch (MalformedURLException e) {
                System.out.println("Loi xay ra trong luc rebind");
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}