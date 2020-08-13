package tam_giac_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            CheckTriangleImpl function = new CheckTriangleImpl();
            Registry registry = LocateRegistry.createRegistry(7777);
            try {
                Naming.rebind("rmi://localhost:7777/triangle", function);
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