package SoSanhHang;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        try {
            IMyRemote obj = (IMyRemote) Naming.lookup("rmi://localhost:1099/ABC");

            System.out.println("Chao mung ban de voi GameShow Hay chon gia dung!");
            System.out.println("He thong se dua ra mot mat hang ngau nhien, ban co 7 luot de doan gia cua mat hang");
            System.out.println("BAT DAU!!!");
            String TenMH = obj.randomMatHang();
            System.out.println("San pham duoc lua chon la: " + TenMH);

            Scanner sc = new Scanner(System.in);
            int LuotChoi = 1;

            while (LuotChoi <= 7) {
                System.out.print("Luot du doan thu " + LuotChoi + ": ");
                int n = sc.nextInt();
                String Ketqua = obj.kiemTraDuDoan(TenMH, n);
                System.out.println(Ketqua);
                if (Ketqua.equals("Gia du doan chinh xac!")) {
                    System.out.println("Chuc mung ban da chien thang!");
                    return;
                }
                LuotChoi++;
            }
            if (LuotChoi > 7) {
                System.out.println("Chuc ban may man lan sau!");
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
