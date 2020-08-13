package calculator_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntCalculator extends Remote {

    public float addition(float x, float y) throws RemoteException;

    public float subtraction(float x, float y) throws RemoteException;

    public float multiplication(float x, float y) throws RemoteException;

    public float division(float x, float y) throws RemoteException;

    public float greatestCommonDivisor(float x, float y) throws RemoteException;

}