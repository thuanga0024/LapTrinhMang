package tam_giac_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            IntCheckTriangle lookup = (IntCheckTriangle) Naming.lookup("rmi://localhost:7777/triangle");
            Scanner input = new Scanner(System.in);
            System.out.print("Nhap canh 1: ");
            int canh1 = Integer.parseInt(input.nextLine());
            System.out.print("Nhap canh 2: ");
            int canh2 = Integer.parseInt(input.nextLine());
            System.out.print("Nhap canh 3: ");
            int canh3 = Integer.parseInt(input.nextLine());
            boolean tamgiac = lookup.CheckTriangle(canh1, canh2, canh3);
            if (tamgiac) {
                System.out.println("Tao duoc tam giac");
            } else {
                System.out.println("Khong tao duoc tam giac");
            }
            System.out.println("Da ket thuc phien");
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
