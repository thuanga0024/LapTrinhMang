/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De1_cau2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author probx
 */
public class Server {
    public static void main(String[] args) {
        ArrayList<CauHoi> listCH = new ArrayList<CauHoi>();
        //doc file
        try {
            FileReader fr = new FileReader("cauhoi.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine())!=null) {                
                String a[] = s.split("\\$");
                CauHoi ch = new CauHoi(a[0], a[1], a[2]);
                listCH.add(ch);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }
        
        
        try {
            ServerSocket myServer = new ServerSocket(2811);
            System.out.println("Da khoi tao server");
            while (true) {                
                Socket socket = myServer.accept();
                System.out.println("Client da ket noi");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                //xu ly
                
                
                for(int i=0;i<listCH.size();i++){
                    String messege = "";
                    CauHoi ch = listCH.get(i);
                    String s = ch.Question +"\n"+ch.Suggestion;
                    dos.writeUTF(s);
                    String dap_an = dis.readUTF();
                    if(dap_an.equalsIgnoreCase(ch.Answer)){
                        messege+="Tra loi dung";
                    }else{
                        messege += "Tra loi sai";
                    }
                    
                    dos.writeUTF(messege);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
