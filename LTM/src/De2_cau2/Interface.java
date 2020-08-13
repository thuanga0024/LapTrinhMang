/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De2_cau2;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author probx
 */
public interface Interface extends Remote{
    public String Tat_Sang_Du(String s) throws RemoteException;
    public String Du_Sang_Tat(String s) throws RemoteException;
}
