/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De8_cau2;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author probx
 */
public interface Interface extends Remote{
    public int Cong(int a,int b) throws RemoteException;
    public int Tru(int a,int b) throws RemoteException;
    public int Nhan(int a,int b) throws RemoteException;
    public float Chia(float a,float b) throws RemoteException;
    public int UCLN(int a,int b) throws RemoteException;
}
