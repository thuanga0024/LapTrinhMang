package VietTat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Remote extends UnicastRemoteObject implements Interface{
    ArrayList<Word> listWord;
    public Remote(ArrayList<Word> list) throws RemoteException {
        super();
        listWord = list;
    }
    
    @Override
    public String Tat_Sang_Du(String s) throws RemoteException {
        for (Word listWord1 : listWord) {
            if(s.equals(listWord1.VietTat)){
                return listWord1.DayDu;
            }
        }
        return "Khong tim thay";
    }

    @Override
    public String Du_Sang_Tat(String s) throws RemoteException {
        for (Word listWord1 : listWord) {
            if(s.equals(listWord1.DayDu)){
                return listWord1.VietTat;
            }
        }
        return "Khong tim thay";
    }
    
}
