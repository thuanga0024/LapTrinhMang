package hay_chon_gia_dung_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HayChonGiaDungimpl extends UnicastRemoteObject implements IntHayChonGiaDung {

    private static final long serialVersionUID = 1L;

    protected HayChonGiaDungimpl() throws RemoteException {
    }

    @Override
    public MatHang getMatHang() {
        MatHang mat_hang = null;

        String sql = "SELECT TOP 1 ma_hang_hoa, ten_hang_hoa FROM mat_hang ORDER BY NEWID()";
        try {
            PreparedStatement preparedStatement = DBProvider.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                mat_hang = new MatHang(resultSet.getString(1), resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mat_hang;
    }

    @Override
    public int checkDuDoan(float giaDuDoan, MatHang matHang) throws RemoteException {

        int res = -2;

        String sql = "SELECT gia_hang_hoa FROM mat_hang WHERE ma_hang_hoa = ?";
        String maHangHoa = matHang.getMaHangHoa();
        try {
            PreparedStatement preparedStatement = DBProvider.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, maHangHoa);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            float giaHangHoa = resultSet.getFloat("gia_hang_hoa");

            if (giaHangHoa > giaDuDoan)
                res = -1;
            else if (giaHangHoa < giaDuDoan)
                res = 1;
            else if (giaHangHoa == giaDuDoan)
                res = 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}