package tra_cuu_tu_dien_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntDictionary extends Remote {
	public String translate_vietnamese_to_english(String vietnamese) throws RemoteException;

	public String translate_english_to_vietnamese(String english) throws RemoteException;

	public String translate_english_to_meaning(String english) throws RemoteException;
}
