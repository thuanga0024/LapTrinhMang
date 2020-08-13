/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau1;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author probx
 */
public interface Interface extends Remote{
    public int binhPhuong(int a) throws RemoteException;
}
