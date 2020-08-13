/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De2_cau2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Server {
    public static void main(String[] args) {
        ArrayList<Word> listWord = new ArrayList<Word>();
        //doc file
        try {
            FileReader fr = new FileReader("Word.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s=br.readLine())!=null) {                
                String a[] = s.split("\\$");
                Word word = new Word(a[0], a[1]);
                listWord.add(word);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }
        
        
        //ket noi
        try {
            Interface ojb = new Remote(listWord);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("ABC", ojb);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
