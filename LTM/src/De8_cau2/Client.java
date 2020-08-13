/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De8_cau2;

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
            Interface ojb = (Interface) Naming.lookup("rmi://localhost:1099/abc");
            Scanner sc = new Scanner(System.in);
            System.out.print("Moi ban nhap a = ");
            int a = sc.nextInt();
            System.out.print("Moi ban nhap b = ");
            int b = sc.nextInt();
            int k =0;
            while (k!=6) {                
                System.out.println("--------------------Calculator--------------");
                System.out.println("1. Cong\n2. Tru\n3. Nhan\n4. Chia\n5. Tim UCLN");
                System.out.println("6. Ket thuc");
                System.out.print("Ban chon: ");
                k = sc.nextInt();
                switch(k){
                    case 1:
                        System.out.println(a+" + "+b+" = "+ojb.Cong(a, b));
                        break;
                    case 2:
                        System.out.println(a+" - "+b+" = "+ojb.Tru(a, b));
                        break;
                    case 3:
                        System.out.println(a+" * "+b+" = "+ojb.Nhan(a, b));
                        break;
                    case 4:
                        float kq = ojb.Chia((float)a, (float)b);                        
                        System.out.println(a+" / "+b+" = "+kq);
                        break;
                    case 5:
                        System.out.println("UCLN cua "+a+" va "+b+" la "+ojb.UCLN(a, b));
                        break;
                    case 6:
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
