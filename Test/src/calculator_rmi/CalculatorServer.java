package calculator_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        CalculatorIpml cal = new CalculatorIpml();
        Registry registry = LocateRegistry.createRegistry(6666);
        Naming.rebind("rmi://localhost:6666/calRemote", cal);

        System.out.println("Server running....");
    }
    
}