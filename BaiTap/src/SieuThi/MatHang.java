
package SieuThi;
public class MatHang {

    public String ID;
    public String TenHang;
    public int GiaTien;
    public int SoLuongTonKho;

    public MatHang(String ID, String TenHang, int GiaTien, int SoLuongTonKho) {
        this.ID = ID;
        this.TenHang = TenHang;
        this.GiaTien = GiaTien;
        this.SoLuongTonKho = SoLuongTonKho;
    }

    public String ThongTinhChiTiet() {
        return "ID: " + ID + "  |  Ten Mat Hang: " + TenHang + "  |  Gia Tien: " + GiaTien + "  |  So luong ton kho: " + SoLuongTonKho;
    }
}
