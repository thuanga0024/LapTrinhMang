package tra_cuu_tu_dien_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static int port = 7777;

    public static void main(String[] args) {
        try {
            DictionaryImpl function = new DictionaryImpl();
            LocateRegistry.createRegistry(port);
            try {
                Naming.rebind("rmi://localhost:7777/getDictionary", function);
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
