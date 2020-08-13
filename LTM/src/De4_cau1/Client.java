/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Client {

    public static void main(String[] args) {
        try {
            Interface ojb = (Interface) Naming.lookup("rmi://localhost:1099/ABC");
            String s ="";
            while (!s.equalsIgnoreCase("ket thuc")) {
                System.out.print("Moi ban nhap a = ");
                int a = new Scanner(System.in).nextInt();
                System.out.println("Binh phuong cua " + a + " la: " + ojb.binhPhuong(a));
                System.out.println("Ban muon tiep tuc hay ket thuc: ");
                s = new Scanner(System.in).nextLine();
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
