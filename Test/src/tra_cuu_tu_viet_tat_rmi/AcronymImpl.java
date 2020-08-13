package tra_cuu_tu_viet_tat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcronymImpl extends UnicastRemoteObject implements IntAcronym {

    protected AcronymImpl() throws RemoteException {
        super();
    }

    @Override
    public String shortToFull(String acronym) throws RemoteException {

        String res = null;
        try {
            Connection con = Util.getConnection();
            String sqlQuery = "Select * from VietTat where tu_viet_tat = ?";

            PreparedStatement ppstm = con.prepareStatement(sqlQuery);
            ppstm.setString(1, acronym);
            ResultSet rs = ppstm.executeQuery();

            if (rs.next())
                res = rs.getString("tu_day_du");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public String fullToShort(String full) throws RemoteException {
        String res = null;

        try {
            Connection con = Util.getConnection();
            String sqlQuery = "Select * from VietTat where tu_day_du = ?";

            PreparedStatement ppstm = con.prepareStatement(sqlQuery);
            ppstm.setString(1, full);
            ResultSet rs = ppstm.executeQuery();

            if (rs.next())
                res = rs.getString("tu_viet_tat");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}