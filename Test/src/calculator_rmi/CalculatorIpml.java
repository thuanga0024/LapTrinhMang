package calculator_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorIpml extends UnicastRemoteObject implements IntCalculator {

    protected CalculatorIpml() throws RemoteException {
    }

    @Override
    public float addition(float x, float y) throws RemoteException {
        return x + y;
    }

    @Override
    public float subtraction(float x, float y) throws RemoteException {
        return x - y;
    }

    @Override
    public float multiplication(float x, float y) throws RemoteException {
        return x * y;
    }

    @Override
    public float division(float x, float y) throws RemoteException {
        return x / y;
    }

    @Override
    public float greatestCommonDivisor(float x, float y) throws RemoteException {

        if (x == 0 || y == 0)
            return x + y;

        while (x != y) {
            if (x > y)
                x -= y;
            else
                y -= x;
        }

        return x;
    }

}