package tra_cuu_tu_dien_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryImpl extends UnicastRemoteObject implements IntDictionary {

    private static final long serialVersionUID = 1L;

    protected DictionaryImpl() throws RemoteException {
        super();
    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:jtds:sqlserver://localhost:1433/dictionary";
        String user = "sa";
        String password = "!@#qwe123";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public List<Word> selectData() {
        List<Word> listWord = new ArrayList<Word>();
        String sql = "SELECT * FROM english";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listWord.add(new Word(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWord;
    }

    @Override
    public String translate_vietnamese_to_english(String vietnamese) throws RemoteException {
        String translateEnglish = null;
        for (Word word : selectData()) {
            if (word.getVietnamese().equals(vietnamese)) {
                translateEnglish = word.getEnglish();
            }
        }
        return translateEnglish;
    }

    @Override
    public String translate_english_to_vietnamese(String english) throws RemoteException {
        String translateVietnamese = null;
        for (Word word : selectData()) {
            if (word.getEnglish().equals(english)) {
                translateVietnamese = word.getVietnamese();
            }
        }
        return translateVietnamese;
    }

    @Override
    public String translate_english_to_meaning(String english) throws RemoteException {
        String translateMeaning = null;
        for (Word word : selectData()) {
            if (word.getEnglish().equals(english)) {
                translateMeaning = word.getMeaning();
            }
        }
        return translateMeaning;
    }

}