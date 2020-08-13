/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De2_cau2;

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
            //xu ly
            Scanner sc = new Scanner(System.in);
            int k = 0;
            while (k!=3) {                
                System.out.println("----------------MENU----------------");
                System.out.println("1. Chuyen tu viet tat sang day du");
                System.out.println("2. Chuyen tu day du sang viet tat");
                System.out.println("3. Ket thuc");
                System.out.print("Ban chon: ");
                k = Integer.parseInt(sc.nextLine());
                switch(k){
                    case 1:
                        System.out.print("Moi ban nhap vao tu viet tat: ");
                        String viettat = sc.nextLine();
                        System.out.println("Tu day du: "+ojb.Tat_Sang_Du(viettat));
                        break;
                    case 2:
                        System.out.print("Moi ban nhap vao tu day du: ");
                        String daydu = sc.nextLine();
                        System.out.println("Tu viet tat: "+ojb.Du_Sang_Tat(daydu));
                        break;
                    case 3:
                        System.out.println("Cam on ban da su dung chuong trinh");
                        break;
                    default:
                        System.out.println("Nhap sai");
                }
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
